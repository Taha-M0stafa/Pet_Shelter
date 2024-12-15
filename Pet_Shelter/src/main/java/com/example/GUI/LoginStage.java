package com.example.GUI;


import com.example.pet_shelter.Main;
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
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginStage implements Initializable {
    //Login/Register Buttons
    @FXML
    public Button ExitProgram;
    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;


    @FXML
    private Label welcomeText;
    @FXML
    private Parent root;
    private Stage stage;
    private TextField text;

    @FXML
    public void onExitProgram(ActionEvent actionEvent) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/login-view.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.close();
    }

    @FXML
    public void onLoginButton(ActionEvent e) throws Exception
    {
        Main m = new Main();
        if(User.login(userField.getText(), passwordField.getText()))
        {
            if(User.loggedInUser.getUserRole().equals("admin")) {
                m.changeScene("/FXML/program-view.fxml");
            }
            else
            {
                m.changeScene("/FXML/program-view.fxml");
            }
        }

    }

    public void onRegisterButton(ActionEvent e) throws Exception
    {
        Main m = new Main();
        m.changeScene("/FXML/Register View.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}