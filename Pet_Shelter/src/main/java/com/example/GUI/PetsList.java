package com.example.GUI;

import com.example.pet_shelter.Pet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PetsList implements Initializable {

    @FXML
    private Text breedText;

    @FXML
    private Text idText;

    @FXML
    private Text nameText;

    @FXML
    private Text speciesText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPetData(Pet pet)
    {
        breedText.setText(pet.getBreed());
        idText.setText(String.valueOf("ID: " + pet.getPetId()));
        nameText.setText(pet.getName());
        speciesText.setText(pet.getSpecies());
    }

}
