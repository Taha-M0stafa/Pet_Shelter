package com.example.pet_shelter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private String shelterID;
    private String shelterName;
    private String shelterLocation;
    private int shelterContactNumber;
    private String shelterEmail;
    private static int shelterCounter;
    private static ArrayList<Pet> pets;
    private static final String FILE_NAME = "Shelters.json";

    // User can swap between multiple shelters In the program to view different pets

    // Pet[] shownPets;

    public Shelter(String shelterID, String shelterName, String shelterLocation, int shelterNumber, String shelterEmail) {
        this.shelterID = shelterID;
        this.shelterName = shelterName;
        this.shelterLocation = shelterLocation;
        this.shelterContactNumber = shelterNumber;
        this.shelterEmail = shelterEmail;
        shelterCounter++;
        this.pets = new ArrayList<>();
    }






    public String getShelterID() {
        return shelterID;
    }

    public void setShelterID(String shelterID) {
        this.shelterID = shelterID;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterLocation() {
        return shelterLocation;
    }

    public void setShelterLocation(String shelterLocation) {
        this.shelterLocation = shelterLocation;
    }

    public int getShelterContactNumber() {
        return shelterContactNumber;
    }

    public void setShelterContactNumber(int shelterContactNumber) {
        this.shelterContactNumber = shelterContactNumber;
    }

    public String getShelterEmail() {
        return shelterEmail;
    }
    public static ArrayList<Pet> getPets() {
        return pets;
    }
    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void setShelterEmail(String shelterEmail) {
        this.shelterEmail = shelterEmail;
    }
    // Add a shelter
    public static void addShelter(Shelter shelter) {
        Main.allShelters.add(shelter);
        System.out.println("Shelter added: " + shelter.getShelterName());
    }

    // Delete a shelter by ID
    public static boolean deleteShelter(String shelterID) {
        for (Shelter shelter : Main.allShelters) {
            if (shelter.getShelterID().equals(shelterID)) {
                Main.allShelters.remove(shelter);
                System.out.println("Shelter deleted: " + shelter.getShelterName());
                return true;
            }
        }
        System.out.println("Shelter not found with ID: " + shelterID);
        return false;
    }

    // Edit a shelter by ID
    public static boolean editShelter(String shelterID, String newName, String newLocation, int newContactNumber, String newEmail) {
        for (Shelter shelter : Main.allShelters) {
            if (shelter.getShelterID().equals(shelterID)) {
                shelter.setShelterName(newName);
                shelter.setShelterLocation(newLocation);
                shelter.setShelterContactNumber(newContactNumber);
                shelter.setShelterEmail(newEmail);
                System.out.println("Shelter updated: " + shelter.getShelterName());
                return true;
            }
        }
        System.out.println("Shelter not found with ID: " + shelterID);
        return false;
    }

    // List all shelters
    public static void listShelters() {
        if (Main.allShelters.isEmpty()) {
            System.out.println("No shelters found.");
        } else {
            for (Shelter shelter : Main.allShelters) {
                System.out.println("ID: " + shelter.getShelterID() +
                        ", Name: " + shelter.getShelterName() +
                        ", Location: " + shelter.getShelterLocation() +
                        ", Contact: " + shelter.getShelterContactNumber() +
                        ", Email: " + shelter.getShelterEmail());
            }
        }
    }

    public static void writeData(List<Shelter> shelters) throws IOException {

        JSONArray jsonArray = new JSONArray();

        for (Shelter shelter : shelters) {
            JSONObject shelterObject = new JSONObject();
            shelterObject.put("shelterID", shelter.getShelterID());
            shelterObject.put("shelterName", shelter.getShelterName());
            shelterObject.put("shelterLocation", shelter.getShelterLocation());
            shelterObject.put("shelterContactNumber", shelter.getShelterContactNumber());
            shelterObject.put("shelterEmail", shelter.getShelterEmail());

            // Create an array of pets for this shelter
            JSONArray petsArray = new JSONArray();
            for (Pet pet : shelter.getPets()) { // shelterPets is the list of pets
                JSONObject petObject = new JSONObject();
                petObject.put("petID", pet.getPetId());
                petObject.put("name", pet.getName());
                petObject.put("species", pet.getSpecies());
                petObject.put("breed", pet.getBreed());
                petObject.put("age", pet.getAge());
                petObject.put("healthStatus", pet.getHealthStatus());
                petsArray.put(petObject);
            }

            shelterObject.put("pets", petsArray); // Attach pets array to the shelter object
            jsonArray.put(shelterObject);
        }

        // Write the JSON array to a file
        try (FileWriter writer = new FileWriter("Shelters.json")) {
            writer.write(jsonArray.toString(4)); // Pretty print JSON with indentation
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        }
    }

    public static List<Shelter> readData() {
        List<Shelter> shelters = new ArrayList<>();
        try {
            Path path = Path.of(FILE_NAME);
            if (!Files.exists(path)) {
                System.out.println("No data file found. Creating a new one...");
            }

            String jsonString = Files.readString(path);
            JSONArray sheltersArray = new JSONArray(jsonString);

            for (int i = 0; i < sheltersArray.length(); i++) {
                JSONObject shelterObject = sheltersArray.getJSONObject(i);
                Shelter shelter = new Shelter(
                        shelterObject.getString("shelterID"),
                        shelterObject.getString("shelterName"),
                        shelterObject.getString("shelterLocation"),
                        shelterObject.getInt("shelterContactNumber"),
                        shelterObject.getString("shelterEmail")

                );

                JSONArray petsArray = shelterObject.getJSONArray("pets");
                for (int j = 0; j < petsArray.length(); j++) {
                    JSONObject petObject = petsArray.getJSONObject(j);
                    Pet pet = new Pet(
                            petObject.getInt("petID"),
                            petObject.getString("name"),
                            petObject.getString("species"),
                            petObject.getString("breed"),
                            petObject.getInt("age"),
                            petObject.getString("healthStatus")
                    );
                    shelter.addPet(pet);
                }
                shelters.add(shelter);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return shelters;
    }

}
