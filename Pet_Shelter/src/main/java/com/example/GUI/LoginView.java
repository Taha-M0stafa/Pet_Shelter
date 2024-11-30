package com.example.GUI;


import com.example.pet_shelter.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;


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