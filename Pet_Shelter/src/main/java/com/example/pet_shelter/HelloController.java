package com.example.pet_shelter;


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

public class HelloController implements Initializable {
    //Login/Register Buttons
    @FXML
    public Button ExitProgram;
    @FXML
    private Button registerButton;
    @FXML
    private Button Login;



    @FXML
    private Label welcomeText;
    @FXML
    private Parent root;
    private Stage stage;
    private TextField text;

    @FXML
    public void onExitProgram(ActionEvent actionEvent) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.close();
    }

    @FXML
    public void onLoginButton(ActionEvent e) throws Exception
    {
        HelloApplication m = new HelloApplication();
        m.changeScene("Login View.fxml");
    }

    public void onRegisterButton(ActionEvent e) throws Exception
    {
        HelloApplication m = new HelloApplication();
        m.changeScene("Register View.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}