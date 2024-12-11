package com.example.pet_shelter;

import com.example.pet_shelter.User;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User {
    private  List<String> adoptionRequests;
    private  List<String> notifications;
    private ArrayList<Adopter>adopterProfiles; //admin


    // Constructor
    public AdminUser(int id, String username, String password, String email, int phoneNum , String address) {
        super(id, username, password, "admin", email, new ContactInformation(phoneNum, address));
        this.adoptionRequests = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.adopterProfiles = new ArrayList<>();

    }

    public AdminUser(User user)
    {
        super(user.getId(), user.getUserName(), user.getUserPassword(), "admin", user.getUserEmail(), user.contactInfo);

    }

    public void addAdopter(Adopter adopter){

        adopterProfiles.add(adopter);
        System.out.println("Adopter profile added: " + adopter.getUserName());

    }


    public void editAdopter(Adopter adopterId, String newName,String newEmail, int newPhoneNumber){
        for (Adopter adopter : adopterProfiles) {
            if (adopter.equals(adopterId)) {
                adopter.setUserName(newName);
                adopter.setUserEmail(newEmail);
                adopter.getContactInfo().phoneNumber = (newPhoneNumber);
            }

        }

    }

    public void deleteAdopter(int adopterId) {
        for (int i=0;i<adopterProfiles.size();i++) {
            if (adopterProfiles.get(i).getId() == adopterId) {
                System.out.println("Adopter profile removed: " + adopterProfiles.get(i).getUserName());
                adopterProfiles.remove(i);
                return;
            }

        }
        System.out.println("couldn't find that id ");
    }

    public void displayAllAdopters(){
        if (adopterProfiles.isEmpty()){
            System.out.println("No adopters yet");
            return;
        }
        for (Adopter adopter:adopterProfiles){
            System.out.println("Adopter ID: " + adopter.getId());
            System.out.println("Adopter Name: " + adopter.getUserName());
            System.out.println("Number of pets: " + adopter.numOfadoptedPets);
            System.out.println("--------------------");
            System.out.println();
        }
    }

    // Handle adoption requests
    public void addAdoptionRequest(String request) {
        adoptionRequests.add(request);
       // remember put this in GUI
        System.out.println("Adoption request added: " + request);
    }

    public void viewAdoptionRequests() {
        System.out.println("Adoption Requests:");
        for (String request : adoptionRequests) {
            System.out.println("- " + request);
        }
    }

    public void approveAdoptionRequest(String request) {
        if (adoptionRequests.remove(request)) {
            notifications.add("Request approved: " + request);
            System.out.println("Approved adoption request: " + request);
        } else {
            System.out.println("Request not found: " + request);
        }
    }

    // Manage notifications
    public void viewNotifications() {
        System.out.println("Notifications:");
        for (String notification : notifications) {
            System.out.println("- " + notification);
        }
    }

    public List<String> getAdoptionRequests() {
        return adoptionRequests;
    }

    public List<String> getNotifications() {
        return notifications;
    } }
//    public void clearNotifications() {
//        notifications.clear();
//        System.out.println("All notifications cleared.");
//    }
//
//    // Manage user accounts
//    public void updateUserAccount(User user, String newUsername, String newPassword) {
//        user.setUsername(newUsername);
//        user.setPassword(newPassword);
//        System.out.println("Updated account for user: " + user.getUsername());
//    }
//
//    public void deleteUserAccount(List<User> users, User user) {
//        if (users.remove(user)) {
//            System.out.println("Deleted account for user: " + user.getUsername());
//        } else {
//            System.out.println("User not found.");
//        }
//    }

    // Override displayInfo

