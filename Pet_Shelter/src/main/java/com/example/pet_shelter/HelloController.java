package com.example.pet_shelter;

import com.almasb.fxgl.entity.action.Action;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    public Button ExitProgram;
    @FXML
    private Label welcomeText;
    @FXML
    private Parent root;
    private Stage stage;
    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void ExitProgram(ActionEvent actionEvent) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.close();
    }


    @FXML
    public void nextScene(ActionEvent e) throws Exception
    {
        HelloApplication m = new HelloApplication();
        m.changeScene("Login View.fxml");
    }

}