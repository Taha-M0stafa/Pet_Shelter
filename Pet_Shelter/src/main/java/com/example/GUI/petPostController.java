package com.example.GUI;


import com.example.pet_shelter.Pet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class petPostController implements Initializable {

    @FXML
    private Label breedLabel;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView petImage;

    @FXML
    private Label speciesLabel;


    private Pet pet;

    private petListener PetListener;


    public void setPostData(Pet pet, petListener PetListener)
    {



        this.PetListener = PetListener;
        this.pet = pet;
        nameLabel.setText(nameLabel.getText() +  pet.getName());
        breedLabel.setText(breedLabel.getText() + pet.getBreed());
        speciesLabel.setText( speciesLabel.getText()  + pet.getSpecies());
        petImage.setImage(pet.getPetImage());
    }


    public void getPostDetails(MouseEvent mouseEvent)
    {
        PetListener.onClickPet(pet);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}