package com.example.pet_shelter;

import com.example.Exceptions.AlreadyFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//My Fav User Ever Go To Hell
public  abstract class User {
    final int id;
    private String user_name;
    private String user_password;
    private String user_role;
    private String user_email;
    private int age;
    private String gender;
    public ContactInformation contactInfo;


    // File to store user data
    private static final String FILE_NAME = "users.json";
    public static User loggedInUser;

    public User(int id, String user_name, String user_password, String user_role, String user_email, int age, String gender, ContactInformation contactInfo) {

        this.id = id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_role = user_role;
        this.user_email = user_email;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;

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

    public int getId() {return id;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    // Login method__
    public static boolean login(String userName, String password) throws AlreadyFoundException {


        for (User user : Main.currentUsers) {
            if (user.getUserName().equals(userName) && user.getUserPassword().equals(password)) {
                System.out.println("Login successful أهلا بيك");
                loggedInUser = user;
                if(loggedInUser.getUserRole().equals("admin"))
                {
                    loggedInUser = new AdminUser(user);
                }
                return true;
            }
        }
        throw new AlreadyFoundException("Invalid password حاول مرة اخرى ربنا يكرمك");
    }



    // Register method__
    public static void register(Adopter newUser) throws AlreadyFoundException {

        for (User user : Main.currentUsers) {
            if (user.getUserEmail().equals(newUser.getUserEmail())) {
                throw new AlreadyFoundException("Email already registered.");
            }
            else if(user.getId() == newUser.getId()) {
                throw new AlreadyFoundException("ID already registered.");
            }
        }
        Main.currentUsers.add(newUser);
        System.out.println("Registration successful!");
    }

    // Generic function to read user data from JSON file
    public static List<Adopter> readData() {
        List<Adopter> users = new ArrayList<>();
        try {
            // Specify the file path
            Path path = Path.of("Pet_Shelter/users.json");
            byte[] bytes = Files.readAllBytes(path);
            String jsonString = new String(bytes);
            JSONArray jsonArray = new JSONArray(jsonString);

            // Iterate through the array and add User objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Adopter adopter = new Adopter(
                        obj.getInt("id"),
                        obj.getString("user_name"),
                        obj.getString("user_password"),
                        obj.getString("user_role"),
                        obj.getString("user_email"),
                        obj.getInt("age"),
                        obj.getString("gender"),
                        obj.getInt("phoneNum"), obj.getString("address")

                ));
            }
        } catch (IOException e) {
            System.out.println("No data file found. Creating a new one...");
        }
        return users;
    }

    // Generic function to write user data to JSON file
    // Generic function to write user data to JSON file
    public static void writeData(List<Adopter> users) {
        JSONArray jsonArray = new JSONArray();

        for (Adopter adopter : users) {
            JSONObject obj = new JSONObject();
            obj.put("id", adopter.getId());
            obj.put("user_name", adopter.getUserName());
            obj.put("user_password", adopter.getUserPassword());
            obj.put("user_role", adopter.getUserRole());
            obj.put("user_email", adopter.getUserEmail());
            obj.put("phoneNum", adopter.getContactInfo().getPhoneNumber());
            obj.put("address", adopter.getContactInfo().getAddress());
            obj.put("gender", adopter.getGender());
            obj.put("age", adopter.getAge());

            // Write adoption history
            JSONArray adoptionHistoryArray = new JSONArray();
            for (AdoptionRequest request : adopter.adoptionHistory) {
                JSONObject requestObj = new JSONObject();

                // Write adoptedPet details
                JSONObject petObj = new JSONObject();
                petObj.put("petId", request.adoptedPet.getPetId());
                petObj.put("name", request.adoptedPet.getName());
                petObj.put("species", request.adoptedPet.getSpecies());
                petObj.put("breed", request.adoptedPet.getBreed());
                petObj.put("age", request.adoptedPet.getAge());
                petObj.put("healthStatus", request.adoptedPet.getHealthStatus());

                requestObj.put("adoptedPet", petObj);
                requestObj.put("adoptionId", request.adoptionId);
                requestObj.put("adoption_Date", request.adoption_Date.toString());
                requestObj.put("adoption_status", request.getStatus().toString());

                adoptionHistoryArray.put(requestObj);
            }
            obj.put("adoptionHistory", adoptionHistoryArray);


            // Write current pets
            JSONArray currentPetsArray = new JSONArray();
            for (Pet pet : adopter.currentPets) {
                JSONObject petObj = new JSONObject();
                petObj.put("petId", pet.getPetId());
                petObj.put("name", pet.getName());
                petObj.put("species", pet.getSpecies());
                petObj.put("breed", pet.getBreed());
                petObj.put("age", pet.getAge());
                petObj.put("healthStatus", pet.getHealthStatus());
                currentPetsArray.put(petObj);
            }
            obj.put("currentPets", currentPetsArray);

            jsonArray.put(obj);
        }

        try (FileWriter writer = new FileWriter("users.json")) {
            // Format 4 spaces
            writer.write(jsonArray.toString(4));
            System.out.println("Data successfully written to file.");
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        }
    }



    // to use in the search for both users and admins
    public void displayAllPets(ArrayList <Pet> pets){
        if (pets.isEmpty()){
            System.out.println("No Pets yet");
            return;
        }
        for (Pet pet : pets) {
            System.out.println("Pet ID: " + pet.getPetId());
            System.out.println("Name: " + pet.getName());
            System.out.println("Age: " + pet.getAge());
            System.out.println("Breed: " + pet.getBreed());
        }
    }

    public ContactInformation getContactInfo(){return contactInfo;}
}

