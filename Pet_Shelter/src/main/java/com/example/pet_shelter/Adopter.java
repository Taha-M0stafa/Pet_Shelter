package com.example.pet_shelter;

import java.util.ArrayList;

public class Adopter extends User {
    public int numOfadoptedPets;
    public ArrayList<AdoptionRequest>adoptionHistory;
    public ArrayList<Pet>currentPets;

    public Adopter(int id, String user_name, String user_password, String user_role, String user_email, int age, String gender, int phoneNumber, String address) {
        super(id, user_name, user_password, user_role, user_email, age, gender, new ContactInformation(phoneNumber, address));
        this.numOfadoptedPets = 0;
        this.adoptionHistory = new ArrayList<>();
        this.currentPets = new ArrayList<>();
    }

    public Adopter(User user)
    {
        super(user.getId(), user.getUserName(), user.getUserPassword(), user.getUserRole(), user.getUserEmail(), user.getAge(), user.getGender(), user.getContactInfo());
    }

    public ArrayList<AdoptionRequest> getAdoptionHistory() {
        return adoptionHistory;
    }

    public ContactInformation getContactInfo() {
        return contactInfo;
    }

    public  void requestAdoption(Pet pet){

            numOfadoptedPets++;
            currentPets.add(pet);
    }



    private int numberOfCurrentPetsForNotification=numOfadoptedPets;
    public int getNumberOfCurrentPetsForNotification() {
        return numberOfCurrentPetsForNotification;
    }

}


