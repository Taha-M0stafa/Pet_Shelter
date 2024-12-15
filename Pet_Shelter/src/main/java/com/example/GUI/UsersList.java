package com.example.GUI;

import com.example.pet_shelter.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersList implements Initializable {

    @FXML
    private Button modifyButton;

    @FXML
    private Text userEmail;

    @FXML
    private Text userName;

    @FXML
    private Text userRole;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUserData(User user)
    {
        userName.setText(user.getUserName());
        userEmail.setText(user.getUserEmail());
        userRole.setText(user.getUserRole());
    }

}
