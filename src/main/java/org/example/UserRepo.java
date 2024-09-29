package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    String insertUserSQL = "INSERT INTO Users (gender, email, phone, cell, nat) VALUES (?, ?, ?, ?, ?) RETURNING user_id";
    String insertNameSQL = "INSERT INTO Names (user_id, title, first, last) VALUES (?, ?, ?, ?)";
    String insertLocationSQL = "INSERT INTO Locations (user_id, street_number, street_name, city, state, country, postcode, latitude, longitude, timezone_offset, timezone_description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String insertLoginSQL = "INSERT INTO Logins (user_id, uuid, username, password, salt, md5, sha1, sha256) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String insertDOBSQL = "INSERT INTO DateOfBirths (user_id, birth_date, age) VALUES (?, ?, ?)";
    String insertRegisteredSQL = "INSERT INTO Registered (user_id, registration_date, registration_age) VALUES (?, ?, ?)";
    String insertIDSQL = "INSERT INTO IDs (user_id, name, value) VALUES (?, ?, ?)";
    String insertPictureSQL = "INSERT INTO Pictures (user_id, large, medium, thumbnail) VALUES (?, ?, ?, ?)";

    String userQuery = "SELECT * FROM Users WHERE user_id = ?";
    String nameQuery = "SELECT * FROM Names WHERE user_id = ?";
    String locationQuery = "SELECT * FROM Locations WHERE user_id = ?";
    String loginQuery = "SELECT * FROM Logins WHERE user_id = ?";
    String dobQuery = "SELECT * FROM DateOfBirths WHERE user_id = ?";
    String registeredQuery = "SELECT * FROM Registered WHERE user_id = ?";
    String idQuery = "SELECT * FROM IDs WHERE user_id = ?";
    String pictureQuery = "SELECT * FROM Pictures WHERE user_id = ?";

    String allUsersQuery = "SELECT * FROM Users";



    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection()) {

            try (PreparedStatement pstmt = connection.prepareStatement(allUsersQuery);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    User user = new User();
                    user.setGender(rs.getString("gender"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setCell(rs.getString("cell"));
                    user.setNat(rs.getString("nat"));
                    int userId = rs.getInt("user_id");


                    loadUserDetails(connection, user, userId);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve users: " + e.getMessage(), e);
        }

        return users;
    }

    public void add(User user) {
        try (Connection connection = DbConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement stmt = connection.prepareStatement(insertUserSQL)) {
                stmt.setString(1, user.getGender());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPhone());
                stmt.setString(4, user.getCell());
                stmt.setString(5, user.getNat());

                ResultSet generatedKeys = stmt.executeQuery();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    saveName(connection, userId, user.getName());
                    saveLocation(connection, userId, user.getLocation());
                    saveLogin(connection, userId, user.getLogin());
                    saveDateOfBirth(connection, userId, user.getDob());
                    saveRegistered(connection, userId, user.getRegistered());
                    saveID(connection, userId, user.getId());
                    savePicture(connection, userId, user.getPicture());
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Failed to save user: " + e.getMessage(), e);
            }

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(int id) {
        User user = null;

        try (Connection connection = DbConnection.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement(userQuery)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setGender(rs.getString("gender"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    user.setCell(rs.getString("cell"));
                    user.setNat(rs.getString("nat"));
                }
            }

            if (user != null) {
                loadUserDetails(connection, user, id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    private void loadUserDetails(Connection connection, User user, int id) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(nameQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User.Name name = new User.Name();
                name.setTitle(rs.getString("title"));
                name.setFirst(rs.getString("first"));
                name.setLast(rs.getString("last"));
                user.setName(name);
            }
        }

        try (PreparedStatement pstmt = connection.prepareStatement(locationQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User.Location location = new User.Location();
                User.Location.Street street = new User.Location.Street();
                street.setNumber(rs.getInt("street_number"));
                street.setName(rs.getString("street_name"));
                location.setStreet(street);
                location.setCity(rs.getString("city"));
                location.setState(rs.getString("state"));
                location.setCountry(rs.getString("country"));
                location.setPostcode(rs.getString("postcode"));

                User.Location.Coordinates coordinates = new User.Location.Coordinates();
                coordinates.setLatitude(rs.getString("latitude"));
                coordinates.setLongitude(rs.getString("longitude"));
                location.setCoordinates(coordinates);

                User.Location.Timezone timezone = new User.Location.Timezone();
                timezone.setOffset(rs.getString("timezone_offset"));
                timezone.setDescription(rs.getString("timezone_description"));
                location.setTimezone(timezone);

                user.setLocation(location);
            }
        }

        try (PreparedStatement pstmt = connection.prepareStatement(loginQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User.Login login = new User.Login();
                login.setUuid(rs.getString("uuid"));
                login.setUsername(rs.getString("username"));
                login.setPassword(rs.getString("password"));
                login.setSalt(rs.getString("salt"));
                login.setMd5(rs.getString("md5"));
                login.setSha1(rs.getString("sha1"));
                login.setSha256(rs.getString("sha256"));
                user.setLogin(login);
            }
        }

        try (PreparedStatement pstmt = connection.prepareStatement(dobQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User.DateOfBirth dob = new User.DateOfBirth();
                dob.setDate(rs.getTimestamp("birth_date"));
                dob.setAge(rs.getInt("age"));
                user.setDob(dob);
            }
        }

        try (PreparedStatement pstmt = connection.prepareStatement(registeredQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User.Registered registered = new User.Registered();
                registered.setDate(rs.getTimestamp("registration_date"));
                registered.setAge(rs.getInt("registration_age"));
                user.setRegistered(registered);
            }
        }

        try (PreparedStatement pstmt = connection.prepareStatement(idQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User.ID userId = new User.ID();
                userId.setName(rs.getString("name"));
                userId.setValue(rs.getString("value"));
                user.setId(userId);
            }
        }

        try (PreparedStatement pstmt = connection.prepareStatement(pictureQuery)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User.Picture picture = new User.Picture();
                picture.setLargeBytes(rs.getBytes("large"));
                picture.setMediumBytes(rs.getBytes("medium"));
                picture.setThumbnailBytes(rs.getBytes("thumbnail"));
                user.setPicture(picture);
            }
        }
    }

    private void saveName(Connection conn, int userId, User.Name name) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(insertNameSQL)) {
            stmt.setInt(1, userId);
            stmt.setString(2, name.getTitle());
            stmt.setString(3, name.getFirst());
            stmt.setString(4, name.getLast());
            stmt.executeUpdate();
        }
    }

    private void saveLocation(Connection conn, int userId, User.Location location) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(insertLocationSQL)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, location.getStreet().getNumber());
            stmt.setString(3, location.getStreet().getName());
            stmt.setString(4, location.getCity());
            stmt.setString(5, location.getState());
            stmt.setString(6, location.getCountry());
            stmt.setString(7, location.getPostcode());
            stmt.setString(8, location.getCoordinates().getLatitude());
            stmt.setString(9, location.getCoordinates().getLongitude());
            stmt.setString(10, location.getTimezone().getOffset());
            stmt.setString(11, location.getTimezone().getDescription());
            stmt.executeUpdate();
        }
    }

    private void saveLogin(Connection conn, int userId, User.Login login) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(insertLoginSQL)) {
            stmt.setInt(1, userId);
            stmt.setString(2, login.getUuid());
            stmt.setString(3, login.getUsername());
            stmt.setString(4, login.getPassword());
            stmt.setString(5, login.getSalt());
            stmt.setString(6, login.getMd5());
            stmt.setString(7, login.getSha1());
            stmt.setString(8, login.getSha256());
            stmt.executeUpdate();
        }
    }

    private void saveDateOfBirth(Connection conn, int userId, User.DateOfBirth dob) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(insertDOBSQL)) {
            stmt.setInt(1, userId);
            stmt.setTimestamp(2, dob.getDate());
            stmt.setInt(3, dob.getAge());
            stmt.executeUpdate();
        }
    }

    private void saveRegistered(Connection conn, int userId, User.Registered registered) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(insertRegisteredSQL)) {
            stmt.setInt(1, userId);
            stmt.setTimestamp(2, registered.getDate());
            stmt.setInt(3, registered.getAge());
            stmt.executeUpdate();
        }
    }

    private void saveID(Connection conn, int userId, User.ID id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(insertIDSQL)) {
            stmt.setInt(1, userId);
            stmt.setString(2, id.getName());
            stmt.setString(3, id.getValue());
            stmt.executeUpdate();
        }
    }

    private void savePicture(Connection conn, int userId, User.Picture picture) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(insertPictureSQL)) {
            picture.loadImage();
            stmt.setInt(1, userId);
            stmt.setBytes(2, picture.getLargeBytes());
            stmt.setBytes(3, picture.getMediumBytes());
            stmt.setBytes(4, picture.getThumbnailBytes());
            stmt.executeUpdate();
        }
    }
}
