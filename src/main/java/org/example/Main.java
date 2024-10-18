
package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import org.flywaydb.core.Flyway;

public class Main {
    static String dbHost = System.getenv("POSTGRES_HOST");
    static String dbPort = System.getenv("POSTGRES_PORT");
    static String dbName = System.getenv("POSTGRES_DB");
    static String dbUser = System.getenv("POSTGRES_USER");
    static String dbPassword = System.getenv("POSTGRES_PASSWORD");
    static String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);

    public static void main(String[] args) throws IOException {
        String filePath = "/app/src/main/resources/V20241017095013__create_tables.sql";
        try {
            executeSQLFromFile(filePath);
            System.out.println("tables were created");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        int port = 8080;
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", exchange -> {
            StringBuilder responseText = new StringBuilder();

        HttpService httpService = new HttpService();
        UserRepo userRepo = new UserRepo();
        User user = new User();
        HttpResponse<String> response;
        ApiResponse apiResponse;
        Gson gson = new Gson();
        String url = "https://randomuser.me/api/";
        Optional<HttpResponse<String>> httpResponseOptional = httpService.getApiResponse(url);
        if (httpResponseOptional.isPresent()) {
            response = httpResponseOptional.get();
            responseText.append("Response Code: ").append(response.statusCode()).append("<br>");
            responseText.append("Response Body: ").append(response.body()).append("<br>");
            apiResponse = gson.fromJson(response.body(), ApiResponse.class);
            user = apiResponse.getResults().get(0);
            userRepo.add(user);
        } else {
            System.out.println("No API response" + '\n');
        }
        List<User> users = userRepo.getAllUsers();
        int i = 0;

        for (User user1 : users) {
            i++;
            responseText.append("User ").append(i).append(": <br>").append(user1.toString()).append("<br><br>");

        }
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, responseText.toString().getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseText.toString().getBytes());
            output.close();
        });
        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("Server is listening on port " + port);
    }
    public static HttpRequest getRequest(String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
    }
    public static void executeSQLFromFile(String filePath) throws IOException, SQLException {

        Path path = Paths.get(filePath);
        String sql = Files.readString(path);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement()) {
            String[] sqlStatements = sql.split(";");
            for (String sqlStatement : sqlStatements) {
                if (!sqlStatement.trim().isEmpty()) {
                    statement.execute(sqlStatement.trim());
                }
            }
        }
    }

}
