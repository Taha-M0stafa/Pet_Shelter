package com.example.pet_shelter;
import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User {
    private List<String> adoptionRequests;
    private List<String> notifications;

    // Constructor
    public AdminUser(int id, String username, String password, String user_role, String user_email) {
        super(id, username, password, user_role, user_email);
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
    }
}