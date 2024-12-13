package com.example.GUI;

import com.example.Exceptions.AlreadyFoundException;
import com.example.pet_shelter.ContactInformation;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterView implements Initializable {
    @FXML
    private Button createAccount;
    @FXML
    private Button returnButton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField textID;
    @FXML
    private TextField addressText;
    @FXML
    private TextField PhoneNumText;
    @FXML
    private TextField age;
    @FXML
    private TextField gender;


    public FXMLLoader fxmlloader = new FXMLLoader();

    public void onCreateAccount(ActionEvent e) throws AlreadyFoundException, IOException {
        try{
            User newUser = new User(Integer.parseInt(textID.getText()), username.getText(), password.getText(), "Admin", email.getText(), Integer.parseInt(age.getText()), gender.getText(),new ContactInformation(Integer.parseInt(PhoneNumText.getText()), addressText.getText()));

            User.register(newUser);

            Main m = new Main();
            m.changeScene("/FXML/login-view.fxml");
        }
        catch (AlreadyFoundException | NumberFormatException | NullPointerException exception)
        {
            System.out.println(exception.getMessage());
        }
    }


    public void onReturnButton(ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("/FXML/login-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {



    }
}
