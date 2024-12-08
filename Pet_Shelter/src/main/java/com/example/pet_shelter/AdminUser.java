package com.example.pet_shelter;

import com.example.pet_shelter.User;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User {
    private final List<String> adoptionRequests;
    private final List<String> notifications;

    // Constructor
    public AdminUser(int id, String username, String password, String email) {
        super(id, username, password, "admin", email);
        this.adoptionRequests = new ArrayList<>();
        this.notifications = new ArrayList<>();
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

