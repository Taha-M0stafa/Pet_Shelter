package com.example.GUI;


import com.example.Exceptions.AlreadyFoundException;
import com.example.pet_shelter.Adopter;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
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
    private Text idTextField;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField phoneNumText;

    @FXML
    private TextField userNameText;

    @FXML
    private ComboBox<String> userRoleBox;
    @FXML
    private TextField ageText;
    @FXML
    private TextField genderText;

    @FXML
    private ListView<User> userListView = new ListView<User>();
    @FXML
    RadioButton maleRadio;
    @FXML
    RadioButton femaleRadio;



    ToggleGroup group = new ToggleGroup();




    @FXML
    void onAddUser(ActionEvent event)
    {
        try {
            User newUser = new Adopter(Integer.parseInt(idTextField.getText()), userNameText.getText(), passwordText.getText(), userRoleBox.getValue(), emailText.getText(),  Integer.parseInt(ageText.getText()), getGender(),Integer.parseInt(phoneNumText.getText()), addressText.getText());
            User.register(newUser);
            updateCellFactory();
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
        if(findUser() == null)
        {
            return;
        }
        Main.currentUsers.remove(removeUser);
        updateCellFactory();
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
            changedUser = new Adopter(Integer.parseInt(idTextField.getText()), userNameText.getText(), passwordText.getText(), userRoleBox.getValue(), emailText.getText(),Integer.parseInt(ageText.getText()),getGender(),Integer.parseInt(phoneNumText.getText()), addressText.getText());
        }
        catch (AlreadyFoundException | NumberFormatException | NullPointerException e)
        {
            throw new AlreadyFoundException("Missing data");
        }
        Main.currentUsers.remove(FoundUser);
        Main.currentUsers.add(changedUser);
        updateCellFactory();
        TaskSuccessful();

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
        userRoleBox.getItems().addAll("admin", "User");
        userRoleBox.setValue(null);

        //Radio buttons

        maleRadio.setToggleGroup(group);
        femaleRadio.setToggleGroup(group);

        userListView.getItems().addAll(Main.currentUsers);
       updateCellFactory();
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
        userRoleBox.setValue("Role");
        addressText.setText(null);
        phoneNumText.setText(null);
        group.selectToggle(null);
        ageText.setText(null);
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

    private void displayUser(User displayUser)
    {
        idTextField.setText(String.valueOf(displayUser.getId()));
        userNameText.setText(displayUser.getUserName());
        passwordText.setText(displayUser.getUserPassword());
        emailText.setText(displayUser.getUserEmail());
        userRoleBox.setValue(displayUser.getUserRole());
        addressText.setText(displayUser.getContactInfo().getAddress());
        phoneNumText.setText(String.valueOf(displayUser.getContactInfo().getPhoneNumber()));

        if(displayUser.getGender().equalsIgnoreCase("male")){
            maleRadio.setSelected(true);
        }
        else if(displayUser.getGender().equalsIgnoreCase("female")){
            femaleRadio.setSelected(true);
        }


        ageText.setText(String.valueOf(displayUser.getAge()));
    }
    private void updateCellFactory(){

        userListView.getItems().clear();
        userListView.getItems().addAll(Main.currentUsers);

        userListView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> userListView) {
                return new ListCell<User>(){
                    protected void updateItem(User user, boolean empty) {
                        super.updateItem(user, empty);

                        if(!empty) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/users-list.fxml"));
                            AnchorPane anchorPane;
                            try {
                                anchorPane = fxmlLoader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            anchorPane.setPrefWidth(userListView.getWidth());
                            anchorPane.setPrefHeight(USE_COMPUTED_SIZE);
                            anchorPane.setOnMousePressed(MouseEvent->{
                                displayUser(user);
                            });

                            UsersList usersList = (UsersList) fxmlLoader.getController();
                            usersList.setUserData(user);
                            setGraphic(anchorPane);
                        }

                    }
                };
            }
        });
    }
    private String getGender()
    {
        if(maleRadio.isSelected()) {
            return "male";
        }
        else if(femaleRadio.isSelected()) {
            return "female";
        }
        return null;
    }


}
