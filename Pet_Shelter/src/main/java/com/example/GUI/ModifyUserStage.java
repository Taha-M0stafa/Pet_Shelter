package com.example.GUI;


import com.example.Exceptions.AlreadyFoundException;
import com.example.pet_shelter.Adopter;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ModifyUserStage implements Initializable
{

    //A counter for the ShowUser method
    private static int Counter = 0;

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
    private ComboBox<String> userRoleBox;




    @FXML
    void onAddUser(ActionEvent event)
    {
        try {
            User newUser = new Adopter(Integer.parseInt(idTextField.getText()), userNameText.getText(), passwordText.getText(), userRoleBox.getValue(), emailText.getText(), Integer.parseInt(phoneNumText.getText()), addressText.getText());
            User.register(newUser);
            TaskSuccessful();
        }
        catch (NumberFormatException | NullPointerException e)
        {
            throw new AlreadyFoundException("Missing data");
        }
    }

    @FXML
    void onDeleteUser(ActionEvent event)
    {
        User removeUser = findUser();
        Main.currentUsers.remove(removeUser);
        TaskSuccessful();
    }

    @FXML
    void onChangeUser()
    {
        User changedUser = null;
        User FoundUser = findUser();

        if(FoundUser == null)
        {
            return;
        }


        try {
            changedUser = new Adopter(Integer.parseInt(idTextField.getText()), userNameText.getText(), passwordText.getText(), userRoleBox.getValue(), emailText.getText(), Integer.parseInt(phoneNumText.getText()), addressText.getText());
        }
        catch (AlreadyFoundException | NumberFormatException | NullPointerException e)
        {
            throw new AlreadyFoundException("Missing data");
        }
        Main.currentUsers.remove(FoundUser);
        Main.currentUsers.add(changedUser);
        TaskSuccessful();
    }

    @FXML
    void onFindUser(ActionEvent event)
    {
        User displayUser = findUser();
        displayUser(displayUser);
    }


    //Throws a NullPointer Exception if the user is not found
    public User findUser()
    {
        Optional<User> chosenUser = null;
        try {
            //Condition to search for the user, Checks for ID first, If it's not entered check for Email, returns false if both conditions fail
            Predicate<User> findUser = new Predicate<User>() {
                @Override
                public boolean test(User user) {
                    try {
                        if (user.getId() == Integer.parseInt(idTextField.getText())) {
                            return true;
                        }
                    }
                    catch (NumberFormatException e) {
                        if(user.getUserEmail().equals(emailText.getText())) {
                            return true;
                        }
                    }
                    return false;
                }
            };
            //Stream to find the user following the Predicate
            chosenUser = Main.currentUsers.stream()
                    .filter(findUser)
                    .findAny();
            if(chosenUser.isEmpty()) {
                throw new NullPointerException();
            }
        }
        catch(NullPointerException e){
            UserNotFound();
        }
        return chosenUser.orElse(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        userRoleBox.getItems().addAll("admin", "user");
        userRoleBox.setValue(null);
    }
    public void TaskSuccessful()
    {
        Alert sucecssAlert = new Alert(Alert.AlertType.CONFIRMATION, "Task completed successfully", ButtonType.OK);
        sucecssAlert.setHeaderText(null);
        sucecssAlert.setTitle("Success");
        Stage successStage  = (Stage) sucecssAlert.getDialogPane().getScene().getWindow();
        successStage.toFront();
        successStage.setAlwaysOnTop(true);
        successStage.showAndWait();
        idTextField.setText(null);
        userNameText.setText(null);
        passwordText.setText(null);
        emailText.setText(null);
        userRoleBox.setValue(null);
        addressText.setText(null);
        phoneNumText.setText(null);
    }


    private void UserNotFound()
    {
        Alert wrongIDAlert = new Alert(Alert.AlertType.ERROR, "User not found. Try again.", ButtonType.OK, ButtonType.CANCEL);
        Stage alertStage = (Stage) wrongIDAlert.getDialogPane().getScene().getWindow();
        wrongIDAlert.setHeaderText("User Not Found.");
        alertStage.setAlwaysOnTop(true);
        alertStage.showAndWait();
        alertStage.toFront();
    }

    @FXML
    public void onShowNextUser(ActionEvent event)
    {
        User shownUser = Main.currentUsers.get(Counter);
        displayUser(shownUser);

        Counter++;
        if(Counter == Main.currentUsers.size()) {
            Counter = 0;
        }

    }

    private void displayUser(User displayUser)
    {
        idTextField.setText(String.valueOf(displayUser.getId()));
        userNameText.setText(displayUser.getUserName());
        passwordText.setText(displayUser.getUserPassword());
        emailText.setText(displayUser.getUserEmail());
        userRoleBox.setValue(displayUser.getUserRole());
        addressText.setText(displayUser.getContactInfo().getAddress());
        phoneNumText.setText(String.valueOf(displayUser.getContactInfo().getPhoneNumber()));
    }

}
