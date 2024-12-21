package com.example.GUI;

import com.example.Exceptions.AlreadyFoundException;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.Pet;
import com.example.pet_shelter.Shelter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ModifyPetStage implements Initializable {

    private static int Counter = 0;

    @FXML
    private TextField breedText;

    @FXML
    private TextField petAgeText;

    @FXML
    private Text idText;

    @FXML
    private TextField petNameText;

    @FXML
    private ComboBox<String> petSpeciesBox;
    @FXML
    private TextField healthStatusText;

    @FXML
    private ListView<Pet> petListView;
    @FXML
    private ComboBox<Shelter> ShelterBox;
   public Notifications notifications;

    @FXML
    void onAddPet(ActionEvent event) {
        try
        {
            int petId=0;
            if(Main.allPets.isEmpty()){petId=1;}
            else{petId=Main.allPets.getLast().getPetId()+1;}
            Pet newPet = new Pet(petId, petNameText.getText(), petSpeciesBox.getValue(), breedText.getText(), Integer.parseInt(petAgeText.getText()), healthStatusText.getText(), ShelterBox.getValue().getShelterName());
            Pet.addPet(newPet, ShelterBox.getValue());
            petListView.getItems().add(newPet);
            UpdateCellFactory();
            TaskSuccessful();
        }
        catch (NumberFormatException | NullPointerException e)
        {
            throw new AlreadyFoundException("Missing data");
        }

    }

    @FXML
    void onChangePet(ActionEvent event) {
        Pet changedPet = null;
        Pet foundPet = petListView.getSelectionModel().getSelectedItem();

        if(foundPet == null)
        {
            throw new AlreadyFoundException("Please select a pet");
        }

        try {
            changedPet = new Pet(Integer.parseInt(idText.getText()),petNameText.getText(), petSpeciesBox.getValue(), breedText.getText(), Integer.parseInt(petAgeText.getText()), healthStatusText.getText(), ShelterBox.getValue().getShelterName());
        }
        catch (AlreadyFoundException | NumberFormatException | NullPointerException e)
        {
            throw new AlreadyFoundException("Missing data");
        }
        ShelterBox.getValue().getPets().remove(foundPet);
        petListView.getItems().remove(foundPet);
        ShelterBox.getValue().getPets().add(changedPet);
        petListView.getItems().add(changedPet);

        UpdateIDs();
        UpdateCellFactory();
        TaskSuccessful();
    }

    @FXML
    void onDeletePet(ActionEvent event) {
        Pet foundPet = petListView.getSelectionModel().getSelectedItem();
        if(foundPet == null)
        {
            throw new AlreadyFoundException("Select a Pet");
        }
        ShelterBox.getValue().getPets().remove(foundPet);
        petListView.getItems().remove(foundPet);
        UpdateCellFactory();
        TaskSuccessful();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        petSpeciesBox.getItems().addAll("Cat", "Dog");
        for(Shelter shelter : Main.allShelters)
        {
            petListView.getItems().addAll(shelter.getPets());
            ShelterBox.getItems().add(shelter);
        }



        ShelterBox.setCellFactory(new Callback<ListView<Shelter>, ListCell<Shelter>>() {

            @Override
            public ListCell<Shelter> call(ListView<Shelter> shelterListView) {
                return new ListCell<Shelter>(){
                    protected void updateItem(Shelter shelter, boolean empty) {
                        super.updateItem(shelter, empty);
                        if(!empty)
                        {
                            Text text = new Text();
                            text.setText(shelter.getShelterName());
                            setGraphic(text);
                        }
                    }

                };
            }
        });

        ShelterBox.setButtonCell(new ListCell<Shelter>()
        {
            protected void updateItem(Shelter shelter, boolean empty) {
                super.updateItem(shelter, empty);
                if(!empty)
                {
                    Text text = new Text();
                    text.setText(shelter.getShelterName());
                    setGraphic(text);
                }
            }

        });

        UpdateCellFactory();

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
        idText.setText(null);
        breedText.clear();
        petAgeText.clear();
        healthStatusText.clear();
        petNameText.clear();
        petSpeciesBox.setValue(null);
    }

    private void displayPet(Pet displayPet)
    {
        Shelter parentShelter = null;

        idText.setText(String.valueOf(displayPet.getPetId()));
        breedText.setText(displayPet.getBreed());
        petAgeText.setText(String.valueOf(displayPet.getAge()));
        healthStatusText.setText(displayPet.getHealthStatus());
        petNameText.setText(displayPet.getName());
        petSpeciesBox.setValue(displayPet.getSpecies());

        for(Shelter shelter : Main.allShelters)
        {
            if(shelter.getShelterName().equals(displayPet.shelterName))
            {
                parentShelter = shelter;
                break;
            }
        }

        ShelterBox.setValue(parentShelter);
    }



    private void UpdateCellFactory()
    {
        petListView.setCellFactory(new Callback<ListView<Pet>, ListCell<Pet>>()
        {
            @Override
            public ListCell<Pet> call(ListView<Pet> petListView)
            {
                return new ListCell<Pet>(){
                    protected void updateItem(Pet pet, boolean empty)
                    {
                        super.updateItem(pet, empty);
                        if(!empty)
                        {




                          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/pets-list.fxml"));
                          AnchorPane anchorPane;

                          try {
                              anchorPane = fxmlLoader.load();
                          } catch (IOException e) {
                                throw new RuntimeException(e);
                         }

                          PetsList petsList = (PetsList)fxmlLoader.getController();
                         petsList.setPetData(pet);

                         anchorPane.setOnMousePressed(MouseEvent -> {
                            displayPet(pet);
                         });

                         anchorPane.setPrefWidth(petListView.getMaxWidth());
                         setGraphic(anchorPane);





                        }
                    }
                };
            }
        });
    }

    private void UpdateIDs()
    {
        for (int i=0; i < Main.allPets.size(); i++)
        {
            Main.allPets.get(i).petID = i;
        }
    }
}