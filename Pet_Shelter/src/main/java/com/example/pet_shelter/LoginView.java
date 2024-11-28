package com.example.pet_shelter;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

import javafx.animation.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class LoginView {

    @FXML
    private Button returnToView;

    @FXML
    public void returnToView(ActionEvent e) throws IOException
    {

        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }



}