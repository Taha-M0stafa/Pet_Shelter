package com.example.GUI;

import com.example.pet_shelter.Main;
import com.example.pet_shelter.Shelter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseShelterController implements Initializable {

    @FXML
    private GridPane gridPane;

    private Label label;
    @FXML
    private AnchorPane testPane;

    @FXML
    private ListView<Shelter> shelterListView;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label = new Label("Shelter 1");
        updateCellFactory();


    }

    public void updateCellFactory()
    {
        shelterListView.getItems().addAll(Main.allShelters);

        shelterListView.setCellFactory(new Callback<ListView<Shelter>, ListCell<Shelter>>() {
            @Override
            public ListCell<Shelter> call(ListView<Shelter> shelterListView) {
                return new ListCell<>(){
                    protected void updateItem(Shelter shelter, boolean empty) {
                        super.updateItem(shelter, empty);
                        if(!empty)
                        {
                        AnchorPane pane = new AnchorPane();
                        pane.getChildren().add(new Label("Name: "+shelter.getShelterName()));
                        Button button = new Button("Choose");
                        button.setOnAction(event ->
                        {

                            System.out.println("Shelter selected successfully");
                            ProgramStage.chosenShelter = shelter;

                            Main m = new Main();
                            try {
                                m.changeScene("/FXML/program-view.fxml");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        button.setTranslateX(800);
                        pane.getChildren().add(button);

                        setGraphic(pane);
                        }
                    }
                };
            }
        });

    }




}
