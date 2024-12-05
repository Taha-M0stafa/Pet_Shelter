package com.example.GUI;

import com.example.Exceptions.UserException;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;

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

            Main m = new Main();
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
        Main m = new Main();
        m.changeScene("hello-view.fxml");
    }

}
