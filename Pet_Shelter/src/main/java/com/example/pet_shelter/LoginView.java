package com.example.pet_shelter;

import com.almasb.fxgl.entity.action.Action;
import com.sun.tools.javac.Main;
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
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

}