
package org.example;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;


import com.google.gson.Gson;

public class Main {
    public static void main(String[] args)  {
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
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body() + '\n');
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
            System.out.println("user " + i + " :  \n\n" + user1.toString());
        }


    }

    public static HttpRequest getRequest(String url) {
        return HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
    }

}
