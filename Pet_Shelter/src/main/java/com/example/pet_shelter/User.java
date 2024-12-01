package com.example.pet_shelter;

import com.example.Exceptions.UserException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    final Integer id;
    private String user_name;
    private String user_password;
    private String user_role;
    private String user_email;

    // File to store user data
    private static final String FILE_NAME = "users.json";

    public User(int id, String user_name, String user_password, String user_role, String user_email) {

        this.id = id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_role = user_role;
        this.user_email = user_email;

    }

    // Getters and setters
    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUserPassword() {
        return user_password;
    }

    public void setUserPassword(String user_password) {
        this.user_password = user_password;
    }

    public String getUserRole() {
        return user_role;
    }

    public void setUserRole(String user_role) {
        this.user_role = user_role;
    }

    public String getUserEmail() {
        return user_email;
    }

    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }

    public int getId()
        {return id;}

    // Login method__
    public static boolean login(String userName, String password) throws UserException {
        List<User> users = readData();
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getUserPassword().equals(password)) {
                System.out.println("Login successful أهلا بيك");
                return true;
            }
        }
        throw new UserException("Invalid password حاول مرة اخرى ربنا يكرمك");
    }

    public void checkEmptyRegister() throws UserException
    {
        if( this.id.toString().trim().isEmpty() || this.user_name == null || this.user_password == null || this.user_role == null || this.user_email == null || this.user_name.isEmpty() || this.user_password.trim().isEmpty() || this.user_role.trim().isEmpty() || this.user_email.trim().isEmpty())
        {
            throw new UserException("Missing data");
        }
    }

    // Register method__
    public static void register(User newUser) throws UserException {
        List<User> users = readData();
        for (User user : users) {
            if (user.getUserEmail().equals(newUser.getUserEmail())) {
                throw new UserException("Email already registered.");
            }
        }
        users.add(newUser);
        writeData(users);
        System.out.println("Registration successful!");
    }

    // Generic function to read user data from JSON file
    private static List<User> readData() {
        List<User> users = new ArrayList<>();
        try {
            // Specify the file path
            Path path = Path.of("users.json");
            byte[] bytes = Files.readAllBytes(path);
            String jsonString = new String(bytes);
            JSONArray jsonArray = new JSONArray(jsonString);

            // Iterate through the array and add User objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                users.add(new User(
                        obj.getInt("id"),
                        obj.getString("user_name"),
                        obj.getString("user_password"),
                        obj.getString("user_role"),
                        obj.getString("user_email")
                ));
            }
        } catch (IOException e) {
            System.out.println("No data file found. Creating a new one...");
        }
        return users;
    }

    // Generic function to write user data to JSON file
    private static void writeData(List<User> users) {
        JSONArray jsonArray = new JSONArray();

        for (User user : users) {
            JSONObject obj = new JSONObject();
            obj.put("id", user.getId());
            obj.put("user_name", user.getUserName());
            obj.put("user_password", user.getUserPassword());
            obj.put("user_role", user.getUserRole());
            obj.put("user_email", user.getUserEmail());
            jsonArray.put(obj);
        }
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        }
    }


}
