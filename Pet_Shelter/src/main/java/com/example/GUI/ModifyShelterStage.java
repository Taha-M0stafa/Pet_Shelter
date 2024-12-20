package com.example.GUI;

import com.example.Exceptions.AlreadyFoundException;
import com.example.pet_shelter.ContactInformation;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.Shelter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyShelterStage implements Initializable {

    @FXML
    private TextField ContactNumberField;

    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private TextField shelterEmailField;

    @FXML
    private TextField shelterIDField;

    @FXML
    private ListView<Shelter> shelterListView;

    @FXML
    private TextField shelterLocationField;

    @FXML
    private TextField shelterNameField;

    public ObservableList<Shelter> observableShelterList = FXCollections.observableArrayList();



    @FXML
    void onAddShelter(ActionEvent event) {

        String shelterID = shelterIDField.getText();
        String shelterName = shelterNameField.getText();
        String shelterLocation = shelterLocationField.getText();
        String shelterContactNumberText = (ContactNumberField.getText());
        String shelterEmail = shelterEmailField.getText();

        // Validate inputs
        if (shelterID.isEmpty()  || shelterName.isEmpty()  || shelterLocation.isEmpty() || shelterContactNumberText.isEmpty() || shelterEmail.isEmpty()) {
            throw new AlreadyFoundException("Missing Data");
        }

        int shelterContactNumber;
        try {
            shelterContactNumber = Integer.parseInt(shelterContactNumberText);
        } catch (NumberFormatException e) {
            throw new AlreadyFoundException("Invalid Contact Number");
        }

        // Check for duplicate shelterID or shelterName
        try {
            Shelter newShelter = new Shelter(shelterID, shelterName, shelterLocation, shelterContactNumber, shelterEmail);

            // Add the shelter to the list and update the table
            Shelter.addShelter(newShelter);
            observableShelterList.add(newShelter);
            UpdateCellFactory();
            TaskSuccessful();
        } catch (AlreadyFoundException e) {
            System.out.println("Error, shelter already found");
        }

    }

    @FXML
    private void onDeleteShelter(ActionEvent event) {
        Shelter selectedShelter = shelterListView.getSelectionModel().getSelectedItem();

        if (selectedShelter == null) {
            throw new AlreadyFoundException("No shelter selected");
        }

        Main.allShelters.remove(selectedShelter);
        observableShelterList.remove(selectedShelter);
        shelterListView.setItems(observableShelterList);
        UpdateCellFactory();
        TaskSuccessful();

    }

    @FXML
    void onUpdateShelter(ActionEvent event)
    {
        // Get the user input from the text fields
        String shelterID = shelterIDField.getText();
        String shelterName = shelterNameField.getText();
        String shelterLocation = shelterLocationField.getText();
        String shelterContactNumberText = ContactNumberField.getText();
        String shelterEmail = shelterEmailField.getText();

        // Validate inputs
        if (shelterID.isEmpty() || shelterName.isEmpty() || shelterLocation.isEmpty() || shelterContactNumberText.isEmpty() || shelterEmail.isEmpty()) {
        throw new AlreadyFoundException("Missing Data");
    }

        int shelterContactNumber;
        try {
            shelterContactNumber = Integer.parseInt(shelterContactNumberText);
        } catch (NumberFormatException e) {
            throw new AlreadyFoundException("Invalid Contact Number");
        }

        // Get the selected shelter from the TableView
        Shelter selectedShelter = shelterListView.getSelectionModel().getSelectedItem();

        if (selectedShelter == null) {
            throw new AlreadyFoundException("No shelter selected");
        }

// Update the selected shelter with the new values
        Shelter newShelter = new Shelter(shelterID, shelterName, shelterLocation, shelterContactNumber, shelterEmail);

        Shelter.deleteShelter(selectedShelter.getShelterID());
        observableShelterList.remove(selectedShelter);
        try {
            Shelter.addShelter(newShelter);
            observableShelterList.add(newShelter);
        }
        catch (AlreadyFoundException e) {
            System.out.println("Error, shelter already found");
        }
        UpdateCellFactory();
        TaskSuccessful();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        observableShelterList.addAll(Main.allShelters);
        shelterListView.setItems(observableShelterList);
        UpdateCellFactory();

    }

    public void displayShelterData(Shelter shelter)
    {
        ContactNumberField.setText(String.valueOf(shelter.getShelterContactNumber()));
        shelterIDField.setText(String.valueOf(shelter.getShelterID()));
        shelterNameField.setText(shelter.getShelterName());
        shelterEmailField.setText(shelter.getShelterEmail());
        shelterLocationField.setText(shelter.getShelterLocation());
    }

    public void UpdateCellFactory()
    {
        shelterListView.setCellFactory(new Callback<ListView<Shelter>, ListCell<Shelter>>() {
            @Override
            public ListCell<Shelter> call(ListView<Shelter> shelterListView) {
                return new ListCell<>(){
                    protected void updateItem(Shelter shelter, boolean empty) {
                        super.updateItem(shelter, empty);
                        if(!empty)
                        {
                            AnchorPane anchorPane = new AnchorPane();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/shelter-list.fxml"));

                            try {
                                anchorPane = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            anchorPane.setPrefWidth(shelterListView.getWidth());
                            anchorPane.setPrefHeight(USE_COMPUTED_SIZE);

                            anchorPane.setOnMousePressed(MouseEvent -> {

                                displayShelterData(shelter);
                            });
                            ShelterList shelterList = (ShelterList) loader.getController();
                            shelterList.setShelterData(shelter);

                            setGraphic(anchorPane);

                        }
                    }
                };
            }
        });
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
        shelterIDField.setText(null);
        shelterEmailField.setText(null);
        shelterLocationField.setText(null);
        shelterNameField.setText(null);

    }


}
