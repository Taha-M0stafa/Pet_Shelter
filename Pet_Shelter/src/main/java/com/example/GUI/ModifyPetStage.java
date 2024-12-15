package com.example.GUI;

import com.example.Exceptions.AlreadyFoundException;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.Pet;
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
    void onAddPet(ActionEvent event) {
        try
        {
            Pet newPet = new Pet(Main.allPets.getLast().petID + 1, petNameText.getText(), petSpeciesBox.getValue(), breedText.getText(), Integer.parseInt(petAgeText.getText()), healthStatusText.getText());
            Pet.addPet(newPet);
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
        Pet foundPet = findPet();

        if(foundPet == null)
        {
            return;
        }

        try {
            changedPet = new Pet(Integer.parseInt(idText.getText()),petNameText.getText(), petSpeciesBox.getValue(), breedText.getText(), Integer.parseInt(petAgeText.getText()), healthStatusText.getText());
        }
        catch (AlreadyFoundException | NumberFormatException | NullPointerException e)
        {
            throw new AlreadyFoundException("Missing data");
        }
        Main.allPets.remove(foundPet);
        Main.allPets.add(changedPet);
        UpdateIDs();
        UpdateCellFactory();
        TaskSuccessful();
    }

    @FXML
    void onDeletePet(ActionEvent event) {
        Pet foundPet = findPet();


        if(foundPet.equals(null))
        {
            return;
        }
        Main.allPets.remove(foundPet);
        UpdateCellFactory();
        TaskSuccessful();
    }

    @FXML
    void onFindPet(ActionEvent event) {
        Pet displayPet = findPet();
        displayPet(displayPet);
    }

    @FXML
    void onShowNextPet(ActionEvent event) {
        Pet currentPet = Main.allPets.get(Counter);
        displayPet(currentPet);
        Counter++;
        if(Counter == Main.allPets.size())
        {
            Counter = 0;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        petSpeciesBox.getItems().addAll("Cat", "Dog");
        petSpeciesBox.setValue(null);


        UpdateCellFactory();

    }

    public Pet findPet()
    {
        Optional<Pet> chosenPet = null;
        try {
            //Condition to search for the pet, Checks for ID , returns false if the conditions fails
            Predicate<Pet> findPet = new Predicate<Pet>() {
                @Override
                public boolean test(Pet pet) {
                    if (pet.getPetId() == Integer.parseInt(idText.getText()))
                    {
                        return true;
                    }
                    return false;
                }
            };
            //Stream to find the pet following the Predicate
            chosenPet = Main.allPets.stream()
                    .filter(findPet)
                    .findAny();
            if(chosenPet.isEmpty()) {
                throw new NullPointerException();
            }
        }
        catch(NullPointerException e){
            PetNotFound();
        }
        return chosenPet.orElse(null);
    }

    private void PetNotFound()
    {
        Alert wrongIDAlert = new Alert(Alert.AlertType.ERROR, "Pet not found. Try again.", ButtonType.OK, ButtonType.CANCEL);
        Stage alertStage = (Stage) wrongIDAlert.getDialogPane().getScene().getWindow();
        wrongIDAlert.setHeaderText("Pet Not Found.");
        alertStage.setAlwaysOnTop(true);
        alertStage.showAndWait();
        alertStage.toFront();
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
        idText.setText(String.valueOf(displayPet.getPetId()));
        breedText.setText(displayPet.getBreed());
        petAgeText.setText(String.valueOf(displayPet.getAge()));
        healthStatusText.setText(displayPet.getHealthStatus());
        petNameText.setText(displayPet.getName());
        petSpeciesBox.setValue(displayPet.getSpecies());
    }



    private void UpdateCellFactory()
    {

        petListView.getItems().clear();




        System.out.println(Main.allPets.size());




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
        petListView.getItems().addAll(Main.allPets);
    }

    private void UpdateIDs()
    {
        for (int i=0; i < Main.allPets.size(); i++)
        {
            Main.allPets.get(i).petID = i;
        }
    }
}