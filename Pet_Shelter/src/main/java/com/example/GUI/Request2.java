
package com.example.GUI;

import com.example.pet_shelter.AdoptionRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class Request2 {

    @FXML
    private Label Date;

    public Button AcceptButton, RejectButton;

    @FXML
    private Label Id;

    @FXML
    private Label adId;

    @FXML
    private Label adName;

    @FXML
    private Label adRole;

    @FXML
    private Label petBreed;

    @FXML
    private Label petId;

    @FXML
    private Label petName;

    @FXML
    private Label status;

    AdoptionRequest currentRequest;

@FXML
void setCurrentRequest(AdoptionRequest r){
    currentRequest=r;

}




    @FXML
    void setData(AdoptionRequest request){
      petName.setText(request.adoptedPet.getName());
      petId.setText(String.valueOf(request.adoptedPet.petID));
      petBreed.setText(request.adoptedPet.getBreed());

      adId.setText(String.valueOf( request.adopter.getId()));
      adName.setText(request.adopter.getUserName());
      adRole.setText(request.adopter.getUserRole());

      Id.setText(String.valueOf(request.adoptionId));
      Date.setText(String.valueOf(request.adoption_Date));
      status.setText(String.valueOf(request.getStatus()));

    }






}
