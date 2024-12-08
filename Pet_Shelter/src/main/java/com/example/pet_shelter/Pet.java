package com.example.pet_shelter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Pet {

    private final int ID;
    public String name;




    public String species;
    public String breed;
    public int age;
    public String healthStatus;

    public Pet(int ID, String name, String species, String breed, int age, String healthStatus) {
        this.ID = ID;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.healthStatus = healthStatus;
    }

    public int getID() {
        return ID;
    }




    public void addPet(Pet pet)
    {
        Main.allPets.add(pet);
    }

    public static List<Pet> readData() {

        List<Pet> pets = null;
        try {
            // Specify the file path
            Path path = Path.of("pets.json");
            byte[] bytes = Files.readAllBytes(path);
            String jsonString = new String(bytes);
            JSONArray jsonArray = new JSONArray(jsonString);
            pets = new ArrayList<>();
            // Iterate through the array and add User objects
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                pets.add(new Pet(
                        obj.getInt("id"),
                        obj.getString("name"),
                        obj.getString("species"),
                        obj.getString("breed"),
                        obj.getInt("age"),
                        obj.getString("healthStatus")
                ));
            }
        } catch (IOException e) {
            System.out.println("No data file found. Creating a new one...");
        }
        return pets;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    // Generic function to write user data to JSON file
    public static void writeData(List<Pet> pets) {
        JSONArray jsonArray = new JSONArray();

        for (Pet pet : pets) {
            JSONObject obj = new JSONObject();
            obj.put("id", jsonArray.length()-1);
            obj.put("name", pet.getName());
            obj.put("species", pet.getSpecies());
            obj.put("breed", pet.getBreed());
            obj.put("age", pet.getAge());
            obj.put("healthStatus", pet.getHealthStatus());
            jsonArray.put(obj);
        }
        try (FileWriter writer = new FileWriter("Pets.json")) {
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        }
    }

}
