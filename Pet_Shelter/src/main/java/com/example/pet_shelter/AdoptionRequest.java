package com.example.pet_shelter;
import java.time.LocalDate;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.Exceptions.AlreadyFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.example.pet_shelter.Main.*;


public class AdoptionRequest {
    Integer adoptionId;
    protected static int Next_ID=0;
    protected Pet adoptedPet;
    protected Adopter adopter;
    protected LocalDate adoption_Date;
    private AdoptionStatus adoption_status = AdoptionStatus.PENDED;

    public Adopter getAdopter() {
        return adopter;
    }

    public AdoptionRequest(int id, Adopter u, Pet p, LocalDate d, AdoptionStatus ad) {
//
        this.adoptionId = id;
        this.adopter = u;
        this.adoptedPet = p;
        this.adoption_Date =(d!=null)?d: LocalDate.now();
        this.adoption_status =(d!=null)?ad: AdoptionStatus.PENDED;
    }

    @Override
    public String toString() {
        return "AdoptionRequest{" +
                "adoptionId=" + adoptionId +
                ", adoptedPet=" + adoptedPet.toString() +
                ", adopterID=" + adopter.getId() +
                ", adoption_Date=" + adoption_Date +
                ", adoption_status=" + adoption_status +
                '}';
    }

    public void setStatus(AdoptionStatus s) {
        adoption_status = s;
    }

    public AdoptionStatus getStatus() {
        return adoption_status;
    }

    public enum AdoptionStatus {
        APPROVED, REJECTED, PENDED
    }

    //adoption request method
    public static void adopt( Pet p) {

       if(requests.isEmpty()){
           Next_ID=1;
       }
       else {
           Next_ID= requests.getLast().adoptionId++;
       }
        AdoptionRequest newRequest = new AdoptionRequest(Next_ID,(Adopter)User.loggedInUser,p,null,null);
        requests.add(newRequest);
        System.out.println("your request have been submitted successfully ");// pop message in the GUI
    }

    //--------------------Admin methods--------------------
    public static void displayPendedRequests() {
        for(AdoptionRequest req:requests){
            if(req.getStatus()==AdoptionStatus.PENDED){
            System.out.println("Adoption ID: " + req.adoptionId );
            System.out.println(  ", Adopter: " + req.adopter.getUserName());
            System.out.println(  ", Pet: " + req.adoptedPet.getName());
            System.out.println(", Status: " + req.getStatus());}
        }
    }

    public static void displayAllRequests() {
        for(AdoptionRequest req:requests){
            System.out.println("Adoption ID: " + req.adoptionId );
            System.out.println(  ", Adopter: " + req.adopter.getUserName());
            System.out.println(  ", Pet: " + req.adoptedPet.getName());
            System.out.println(", Status: " + req.getStatus());
        }
    }

    // Method to reject the request
    public void rejectRequest(AdoptionRequest request) {
        request.setStatus(AdoptionStatus.REJECTED);
        System.out.println("Adoption Request Rejected for Pet: " + request.adoptedPet.getPetId());
    }

    // Method to approve the request
    public void approveRequest(AdoptionRequest request) {
        request.setStatus(AdoptionStatus.APPROVED);
        System.out.println("Adoption Request Approved for Pet: " + request.adoptedPet.getPetId());
        request.adopter.requestAdoption(request.adoptedPet); //for adopter class
         //removing the accepted pet from the pets list
        allPets.remove(request.adoptedPet);
    }
//------------------------------------------------------------

    public static List<AdoptionRequest> readData() {
       List<AdoptionRequest> adoptionRequests = new ArrayList<>();

        try {
            // Step 1: Specify file path and read data
            Path path = Path.of("Requests.json");
            byte[] bytes = Files.readAllBytes(path);
            String jsonString = new String(bytes);

            // Step 2: Parse JSON array
            JSONArray jsonArray = new JSONArray(jsonString);

            // Step 3: Iterate through JSON objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                // Step 4: Extract adopter and pet details
                JSONObject adopterObj = obj.getJSONObject("adopter");
                Adopter adopter = new Adopter(
                        adopterObj.getInt("id"),
                        adopterObj.getString("user_name"),
                        adopterObj.getString("user_password"),
                        adopterObj.getString("user_role"),
                        adopterObj.getString("user_email"),
                        adopterObj.getInt("age"),
                        adopterObj.getString("gender"),
                        adopterObj.getInt("phoneNumber"),
                        adopterObj.getString("address")
                );

                JSONObject petObj = obj.getJSONObject("adoptedPet");
                Pet adoptedPet = new Pet(
                        petObj.getInt("petId"),
                        petObj.getString("name"),
                        petObj.getString("species"),
                        petObj.getString("breed"),
                        petObj.getInt("age"),
                        petObj.getString("healthStatus"),
                        "PlaceHolder"
                );


                AdoptionRequest request = new AdoptionRequest(
                        obj.getInt("adoptionId"),
                        adopter,
                        adoptedPet,
                        LocalDate.parse(obj.getString("adoptionDate")),
                        AdoptionStatus.valueOf(obj.getString("adoptionStatus"))
                );


                adoptionRequests.add(request);
            }
        } catch (IOException e) {
            System.out.println("No data found");
        }


        return adoptionRequests;
    }

    public static void writeData(List<AdoptionRequest> adoptionRequests) {
        // Create a JSONArray to hold the data
        JSONArray jsonArray = new JSONArray();

        // Iterate over the list of AdoptionRequest objects and add them to the JSONArray
        for (AdoptionRequest request : adoptionRequests) {
            JSONObject obj = new JSONObject();

            // Add the fields of the AdoptionRequest object to the JSON object
            obj.put("adoptionId", request.adoptionId);
            obj.put("adopter", new JSONObject()
                    .put("id", request.adopter.getId())
                    .put("user_name", request.adopter.getUserName())
                    .put("user_password",request.adopter.getUserPassword())
                    .put("age",request.adopter.getAge())
                    .put("gender",request.adopter.getGender())
                    .put("phoneNumber",request.adopter.getContactInfo().phoneNumber)
                    .put("address",request.adopter.getContactInfo().address)
                    .put("user_role", request.adopter.getUserRole())
                    .put("user_email", request.adopter.getUserEmail())
            );

            obj.put("adoptedPet", new JSONObject()
                    .put("petId", request.adoptedPet.getPetId())
                    .put("name", request.adoptedPet.getName())
                    .put("species", request.adoptedPet.getSpecies())
                    .put("breed", request.adoptedPet.getBreed())
                    .put("age", request.adoptedPet.getAge())
                    .put("healthStatus", request.adoptedPet.getHealthStatus())
            );

            obj.put("adoptionDate", request.adoption_Date.toString());
            obj.put("adoptionStatus", request.adoption_status.toString());

            jsonArray.put(obj);
        }

        try (FileWriter file = new FileWriter("Requests.json")) {
            file.write(jsonArray.toString(4));
            System.out.println("Adoption requests have been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to the file.");
        }
    }
}


/*
for the notification part we have 2 options or we can make both :
in the 2 options we need to add a list as an attribute to the user class

1. make a section for each user called notification and display there the notifications of the current user

2. (preferred option) when the user is logged in if he has a notification so it appears as a pop message

 */