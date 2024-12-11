package com.example.GUI;


import com.example.pet_shelter.Adopter;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddUserStage implements Initializable
{

    @FXML
    private TextField addressText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField phoneNumText;

    @FXML
    private TextField userNameText;
    @FXML
    private AnchorPane MainAnchorPane;


    @FXML
    private Button addUser;
    @FXML
    private ComboBox<String> userRoleBox;
    @FXML
    public boolean closeStage = false;


    @FXML
    void onAddUser(ActionEvent event)
    {
        User newUser = new Adopter(Integer.parseInt(idTextField.getText()), userNameText.getText(), passwordText.getText(), userRoleBox.getValue(), emailText.getText(), Integer.parseInt(phoneNumText.getText()), addressText.getText());
        Main.currentUsers.add(newUser);
        closeStage();
    }

    @FXML
    void onDeleteUser(ActionEvent event)
    {
        User removeUser = findUser();
       Main.currentUsers.remove(removeUser);
       closeStage();


    }

    @FXML
    void onChangeUser()
    {
        User changedUser = findUser();
        Main.currentUsers.remove(changedUser);
        changedUser =  new Adopter(Integer.parseInt(idTextField.getText()), userNameText.getText(), passwordText.getText(), userRoleBox.getValue(), emailText.getText(), Integer.parseInt(phoneNumText.getText()), addressText.getText());
        Main.currentUsers.add(changedUser);
        closeStage();
    }

    @FXML
    void onFindUser(ActionEvent event)
    {
        User displayUser = findUser();

        idTextField.setText(String.valueOf(displayUser.getId()));
        userNameText.setText(displayUser.getUserName());
        passwordText.setText(displayUser.getUserPassword());
        emailText.setText(displayUser.getUserEmail());
        userRoleBox.setValue(displayUser.getUserRole());
        addressText.setText(displayUser.getContactInfo().getAddress());
        phoneNumText.setText(String.valueOf(displayUser.getContactInfo().getPhoneNumber()));

    }


    public User findUser()
    {
        Optional<User> chosenUser =Main.currentUsers.stream()
                .filter(user -> user.getId() == Integer.parseInt(idTextField.getText()))
                .findAny();
        return chosenUser.orElse(null);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        userRoleBox.getItems().addAll("admin", "user");
    }

    public void closeStage()
    {
        Stage stage = (Stage) MainAnchorPane.getScene().getWindow();
        stage.close();

    }
}
