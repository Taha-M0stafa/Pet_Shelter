//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.example.pet_shelter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends Application {
    public static List<Adopter> currentUsers;
    public static List<Pet> allPets = new ArrayList<>();
    public static List<AdoptionRequest> requests;
    public static Stage changeStage;
    public static List<Shelter> allShelters = new ArrayList<>();

    public Main() {
    }

    public void start(Stage stage) throws IOException {
        stage.resizableProperty().setValue(Boolean.TRUE);


        stage.setWidth(1000);
        stage.setHeight(1000);


        changeStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FXML/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Salutations");
        stage.setScene(scene);
        stage.show();}




    public void changeScene(String FXML) throws IOException {
        Parent changeRoot = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource(FXML)));
        changeStage.getScene().setRoot(changeRoot);
        changeStage.setWidth(1000);
        changeStage.setHeight(1000);
        changeStage.show();
    }

    public static void main(String[] args) throws IOException {
        ReadAllData();
        launch();
        WriteAllData();
    }

    public static void ReadAllData() throws IOException {
        currentUsers = User.readData();
        allShelters = Shelter.readData();
        requests=AdoptionRequest.readData();
        AssignRequestsToAdopters();
        AssignPetsToAdopters();

        for(Shelter shelter : allShelters)
        {
            allPets.addAll(shelter.getPets());
        }
    }
    public static void AssignRequestsToAdopters() {
        for (Adopter adopter : currentUsers) {
            for (AdoptionRequest request : requests) {
                if (request.getAdopter().getId() == adopter.getId()) {
                    adopter.adoptionHistory.add(request);
                }
            }
        }
    }


    public static void AssignPetsToAdopters() {
        for (Adopter adopter : currentUsers) {
            for (AdoptionRequest request : requests) {
                if (request.getAdopter().getId() == adopter.getId()&& request.getStatus() == AdoptionRequest.AdoptionStatus.APPROVED){
                    Pet pet = request.adoptedPet;
                    adopter.currentPets.add(pet);
                }
            }
        }

    }

    public static void WriteAllData() throws IOException {
        User.writeData(currentUsers);
        AdoptionRequest.writeData(requests);
        Shelter.writeData(allShelters);
    }


}




