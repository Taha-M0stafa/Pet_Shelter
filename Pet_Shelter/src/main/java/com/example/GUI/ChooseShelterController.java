package com.example.GUI;

import com.example.pet_shelter.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChooseShelterController implements Initializable {

    @FXML
    private GridPane gridPane;

    private Label label;
    private AnchorPane testPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label = new Label("Shelter 1");


        gridPane.getChildren().add(label);
    }



    @FXML
    void chooseShelter(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/FXML/program-view.fxml");

    }




}
