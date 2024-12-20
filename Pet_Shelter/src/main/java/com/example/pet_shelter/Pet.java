package com.example.pet_shelter;

import com.example.Exceptions.AlreadyFoundException;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Pet {

    public int petID;
    public String name;
    private Image petImage;


    public String species;
    public String breed;
    public int age;
    public String healthStatus;

    public Pet(int petID, String name, String species, String breed, int age, String healthStatus)  {
        this.petID = petID;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.healthStatus = healthStatus;

        if(getSpecies().equals("Dog"))
        {
            petImage = new Image(new File("Pet_Shelter/src/main/resources/Photos/Dog.png").toURI().toString());

        }
        else if(getSpecies().equals("Cat"))
        {
            petImage = new Image(new File("Pet_Shelter/src/main/resources/Photos/Cat.png").toURI().toString());
        }

    }

    public static void addPet(Pet newPet, Shelter shelter) throws AlreadyFoundException
    {
        for( Pet pet : Main.allPets)
        {
            if(pet.getPetId() == newPet.getPetId())
            {
                throw new AlreadyFoundException("Pet already exists");
            }
        }
        shelter.getPets().add(newPet);
    }

    public int getPetId() {
        return petID;
    }



//    public static List<Pet> readData() {
//
//        List<Pet> pets = null;
//        try {
//            // Specify the file path
//            Path path = Path.of("pets.json");
//            byte[] bytes = Files.readAllBytes(path);
//            String jsonString = new String(bytes);
//            JSONArray jsonArray = new JSONArray(jsonString);
//            pets = new ArrayList<>();
//            // Iterate through the array and add User objects
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//
//                JSONObject obj = jsonArray.getJSONObject(i);
//                pets.add(new Pet(
//                        i,
//                        obj.getString("name"),
//                        obj.getString("species"),
//                        obj.getString("breed"),
//                        obj.getInt("age"),
//                        obj.getString("healthStatus")
//                ));
//            }
//        } catch (IOException e) {
//            System.out.println("No data file found. Creating a new one...");
//        }
//        return pets;
//    }
//
//
//
//    // Generic function to write user data to JSON file
//    public static void writeData(List<Pet> pets) {
//        JSONArray jsonArray = new JSONArray();
//
//        for (Pet pet : pets) {
//            JSONObject obj = new JSONObject();
//            obj.put("id", pet.getPetId());
//            obj.put("name", pet.getName());
//            obj.put("species", pet.getSpecies());
//            obj.put("breed", pet.getBreed());
//            obj.put("age", pet.getAge());
//            obj.put("healthStatus", pet.getHealthStatus());
//            obj.put("ImgSource", pet.getPetImage().getUrl());
//            jsonArray.put(obj);
//        }
//        try (FileWriter writer = new FileWriter("Pets.json")) {
//            writer.write(jsonArray.toString());
//        } catch (IOException e) {
//            System.out.println("Error writing data to file: " + e.getMessage());
//        }
//    }



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


    public Image getPetImage() {
        return petImage;
    }
}
