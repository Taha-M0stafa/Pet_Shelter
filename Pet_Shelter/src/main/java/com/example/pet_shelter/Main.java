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
import java.util.List;
import java.util.Objects;

public class Main extends Application {
    public static List<User> currentUsers;
    public static List<Pet> allPets;
    public static List<AdoptionRequest> requests;
    public static Stage changeStage;


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

    public static void main(String[] args) {
        ReadAllData();
        launch();
        WriteAllData();
    }

    public static void ReadAllData() {
        currentUsers = User.readData();
        allPets = Pet.readData();
        requests=AdoptionRequest.readData();

    }

    public static void WriteAllData() {
        User.writeData(currentUsers);
        Pet.writeData(allPets);
        AdoptionRequest.writeData(requests);
    }

}