package com.example.pet_shelter;

import com.example.pet_shelter.ContactInformation;
import com.example.pet_shelter.Pet;
import com.example.pet_shelter.User;

import java.util.ArrayList;

public class Adopter extends User {
    public int numOfadoptedPets;
    public ArrayList<Pet>adoptionHistory;
    public ArrayList<Pet>currentPets;
    public ContactInformation contactInfo;


    public Adopter(int id, String user_name, String user_password, String user_role, String user_email,int phoneNumber) {
        super(id, user_name, user_password, user_role, user_email);
        this.numOfadoptedPets = 0;
        this.adoptionHistory = new ArrayList<>();
        this.currentPets = new ArrayList<>();
        this.contactInfo = new ContactInformation();
        contactInfo.name = user_name;
        contactInfo.phoneNumber = (phoneNumber);
        contactInfo.email = (user_email);

    }

    public ArrayList<Pet> getAdoptionHistory() {
        return adoptionHistory;
    }

    public ContactInformation getContactInfo() {
        return contactInfo;
    }
    public void requestAdoption(Pet pet){
        // if (admin.approve())
        {
            adoptionHistory.add(pet);
            numOfadoptedPets++;
            currentPets.add(pet);
        }
//            else {
//                System.out.println("Admin denied your request");
//            }
    }

    public void displayAdoptedPets() {
        if (adoptionHistory.isEmpty()){
            System.out.println("No pets adopted yet");
            return;}
        else {
            for (int i = 0; i < adoptionHistory.size(); i++) {
                System.out.println("Pet number " + (i + 1) + "#");
                System.out.println("Pet id: " + adoptionHistory.get(i).getID());
                System.out.println("Pet name: " + adoptionHistory.get(i).getName());
                System.out.println("Pet age: " + adoptionHistory.get(i).getAge());
                System.out.println("Pet Health Status: " + adoptionHistory.get(i).getHealthStatus());
                System.out.println("------------");
            }
            System.out.println();
        }
    }

   


}


