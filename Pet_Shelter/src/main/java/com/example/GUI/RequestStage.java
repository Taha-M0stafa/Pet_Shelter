package com.example.GUI;

import com.example.pet_shelter.Adopter;
import com.example.pet_shelter.AdoptionRequest;
import com.example.pet_shelter.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestStage implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UpdateCellFactory();
    }

    @FXML
    public ListView <AdoptionRequest>reqlist;

    public void UpdateCellFactory(){
        reqlist.getItems().addAll(Main.requests);
        reqlist.setCellFactory(new Callback<ListView<AdoptionRequest>, ListCell<AdoptionRequest>>() {
            @Override
            public ListCell<AdoptionRequest> call(ListView<AdoptionRequest> reqlist) {
                return new ListCell<>() {
                    protected void updateItem(AdoptionRequest request, boolean empty) {
                        super.updateItem(request, empty);
                        if(!empty){
                            AnchorPane pane = new AnchorPane();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Request2.fxml"));
                            try {
                                pane = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Request2 request2 = (Request2) loader.getController();
                            request2.setData(request);
                            request2.setCurrentRequest(request);
                            pane.setPrefWidth(reqlist.getPrefWidth());
                            pane.setPrefHeight(USE_COMPUTED_SIZE);
                           // System.out.println("I am not empty");
                            setGraphic(pane);
                        }


                    }
                };
            }
        });


    }



}
