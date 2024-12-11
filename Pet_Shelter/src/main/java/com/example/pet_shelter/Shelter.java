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

public class Shelter {
    private String shelterID;
    private String shelterName;
    private String shelterLocation;
    private int shelterContactNumber;
    private String shelterEmail;
    private static int shelterCounter;


    // User can swap between multiple shelters In the program to view different pets

    // Pet[] shownPets;

    public Shelter(String shelterName, String shelterLocation, int shelterNumber, String shelterEmail) {
        this.shelterName = shelterName;
        this.shelterLocation = shelterLocation;
        this.shelterContactNumber = shelterNumber;
        this.shelterEmail = shelterEmail;
        shelterCounter++;
        

    }


    private static final ArrayList<Shelter> shelterList = new ArrayList<>();
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

    public void setShelterEmail(String shelterEmail) {
        this.shelterEmail = shelterEmail;
    }
    // Add a shelter
    public static void addShelter(Shelter shelter) {
        shelterList.add(shelter);
        System.out.println("Shelter added: " + shelter.getShelterName());
    }

    // Delete a shelter by ID
    public static boolean deleteShelter(String shelterID) {
        for (Shelter shelter : shelterList) {
            if (shelter.getShelterID().equals(shelterID)) {
                shelterList.remove(shelter);
                System.out.println("Shelter deleted: " + shelter.getShelterName());
                return true;
            }
        }
        System.out.println("Shelter not found with ID: " + shelterID);
        return false;
    }

    // Edit a shelter by ID
    public static boolean editShelter(String shelterID, String newName, String newLocation, int newContactNumber, String newEmail) {
        for (Shelter shelter : shelterList) {
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
        if (shelterList.isEmpty()) {
            System.out.println("No shelters found.");
        } else {
            for (Shelter shelter : shelterList) {
                System.out.println("ID: " + shelter.getShelterID() +
                        ", Name: " + shelter.getShelterName() +
                        ", Location: " + shelter.getShelterLocation() +
                        ", Contact: " + shelter.getShelterContactNumber() +
                        ", Email: " + shelter.getShelterEmail());
            }
        }
    }
}
