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
    public String shelterName;


    @Override
    public String toString()
    {
        return "Pet{" +
                "petID=" + petID +
                ", name='" + name + '\'' +
                ", petImage=" + petImage +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", healthStatus='" + healthStatus + '\'' +
                '}';
    }



    public Pet(int petID, String name, String species, String breed, int age, String healthStatus, String shelterName)  {
        this.petID = petID;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.healthStatus = healthStatus;
        this.shelterName = shelterName;

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
        for( Pet pet : shelter.getPets())
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
