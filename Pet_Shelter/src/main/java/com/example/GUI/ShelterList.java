package com.example.GUI;

import com.example.pet_shelter.Shelter;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ShelterList {

    @FXML
    private Text shelterEmail;

    @FXML
    private Text shelterIDText;

    @FXML
    private Text shelterName;







    public void setShelterData(Shelter s)
    {
        shelterEmail.setText(s.getShelterEmail());
        shelterIDText.setText(s.getShelterID());
        shelterName.setText(s.getShelterName());
    }



}
