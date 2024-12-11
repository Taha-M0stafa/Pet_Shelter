package com.example.pet_shelter;

public class ContactInformation {
    public int phoneNumber;
    public String name;
    public String email;
    public String address;

    public ContactInformation(){
        this.phoneNumber=-1;
        name="user";
        email="example@gmail.com";
        address="no current address";
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }





}
