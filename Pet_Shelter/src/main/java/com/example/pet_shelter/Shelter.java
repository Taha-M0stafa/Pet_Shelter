package com.example.pet_shelter;

public class Shelter {
    private String shelterID;
    private String shelterName;
    private String shelterLocation;
    private int shelterNumber;
    private String shelterEmail;
    static int shelterCounter;




    public Shelter(String shelterName, String shelterLocation, int shelterNumber, String shelterEmail) {
        this.shelterName = shelterName;
        this.shelterLocation = shelterLocation;
        this.shelterNumber = shelterNumber;
        this.shelterEmail = shelterEmail;
        shelterCounter++;
        

    }

    public String getShelterID() {
        return shelterID;
    }

    public void setShelterID(String shelterID) {
        this.shelterID = shelterID;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterLocation() {
        return shelterLocation;
    }

    public void setShelterLocation(String shelterLocation) {
        this.shelterLocation = shelterLocation;
    }

    public int getShelterNumber() {
        return shelterNumber;
    }

    public void setShelterNumber(int shelterNumber) {
        this.shelterNumber = shelterNumber;
    }

    public String getShelterEmail() {
        return shelterEmail;
    }

    public void setShelterEmail(String shelterEmail) {
        this.shelterEmail = shelterEmail;
    }
}
