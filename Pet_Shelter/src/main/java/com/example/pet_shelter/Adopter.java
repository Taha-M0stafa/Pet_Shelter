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
        this(user.getId(), user.getUserName(), user.getUserPassword(), user.getUserRole(), user.getUserEmail(), user.getAge(), user.getGender(), user.getContactInfo().getPhoneNumber(), user.getContactInfo().getAddress());
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

    public int getNumOfadoptedPets() {
        return numOfadoptedPets;
    }

    public void setNumOfadoptedPets(int numOfadoptedPets) {
        this.numOfadoptedPets = numOfadoptedPets;
    }

    public void setAdoptionHistory(ArrayList<AdoptionRequest> adoptionHistory) {
        this.adoptionHistory = adoptionHistory;
    }

    public ArrayList<Pet> getCurrentPets() {
        return currentPets;
    }

    public void setCurrentPets(ArrayList<Pet> currentPets) {
        this.currentPets = currentPets;
    }

    private int numberOfCurrentPetsForNotification=numOfadoptedPets;
    public int getNumberOfCurrentPetsForNotification() {
        return numberOfCurrentPetsForNotification;
    }

}


