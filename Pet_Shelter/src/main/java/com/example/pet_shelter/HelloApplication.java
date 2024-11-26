package com.example.pet_shelter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.pet_shelter.HelloController;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {


    private static Stage changeStage;


    @Override
    public void start(Stage stage) throws IOException {
        changeStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Salutations");
        stage.setScene(scene);
        stage.show();

    }

    public void changeScene(String FXML) throws IOException {
        Parent changeRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(FXML)));
        changeStage.getScene().setRoot(changeRoot);
        changeStage.show();
    }

    public static void main(String[] args) {

        launch();

    }
}