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

public class LoginView {

    @FXML
    public Button returnToView;

    @FXML
    public void returnToView(ActionEvent e) throws IOException
    {
        try {
            Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root2);
            stage.show();
        }
        catch (Exception ex) {
            System.out.println("Error");
        }
    }

}