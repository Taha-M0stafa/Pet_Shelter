//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.pet_shelter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static List<User> currentUsers;
    public static List<Pet> allPets;
    public static Stage changeStage;


    public void start(Stage stage) throws IOException {
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.sizeToScene();
        changeStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FXML/program-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Salutations");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String FXML) throws IOException {
        Parent changeRoot = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource(FXML)));
        changeStage.getScene().setRoot(changeRoot);
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
    }

    public static void WriteAllData() {
        User.writeData(currentUsers);
        Pet.writeData(allPets);
    }
}