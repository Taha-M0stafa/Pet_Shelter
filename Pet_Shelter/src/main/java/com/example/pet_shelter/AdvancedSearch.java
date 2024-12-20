package com.example.pet_shelter;

import com.example.pet_shelter.Pet;

import java.util.ArrayList;

public class AdvancedSearch {
    public static ArrayList<Pet> SearchUsingName(ArrayList <Pet> pets, String searchKey){
        ArrayList <Pet> searchedPets =new ArrayList<>();

        for (Pet pet :pets){
            if (pet.getName().toLowerCase().contains(searchKey.toLowerCase())){
                searchedPets.add(pet);
            }
        }

        return  searchedPets;

    }
    public static ArrayList<Pet> SearchUsingAge(ArrayList <Pet> pets, int searchKey){
        ArrayList <Pet> searchedPets =new ArrayList<>();

        for (Pet pet :pets){
            if (pet.getAge()== searchKey){
                searchedPets.add(pet);

            }

        }

        return  searchedPets;

    }
    public static ArrayList<Pet> SearchUsingBreed(ArrayList <Pet> pets, String searchKey){
        ArrayList <Pet> searchedPets =new ArrayList<>();

        for (Pet pet :pets){
            if (pet.getBreed().toLowerCase().contains(searchKey.toLowerCase())){
                searchedPets.add(pet);
            }
        }
        return  searchedPets;
    }





}