package com.example.GUI;

import com.example.Exceptions.UserException;
import com.example.pet_shelter.HelloApplication;
import com.example.pet_shelter.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import com.example.pet_shelter.User;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterView {
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

    public void onCreateAccount(ActionEvent e) throws UserException, IOException {


        User newUser = new User(Integer.parseInt(textID.getText()), username.getText(), password.getText(), "Admin", email.getText());

        try{
            newUser.checkEmptyRegister();

            User.register(newUser);

            HelloApplication m = new HelloApplication();
            m.changeScene("hello-view.fxml");
        }
        catch (UserException exception)
        {
            System.out.println(exception.getMessage());
        }
        finally
        {

        }


    }


    public void onReturnButton(ActionEvent e) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");
    }

}
