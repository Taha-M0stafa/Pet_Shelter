package com.example.GUI;

import com.example.pet_shelter.Main;
import com.example.pet_shelter.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProgramView extends AnchorPane implements Initializable {

    @FXML
    private ImageView homeView;

    @FXML
    private Button adminButton;

    @FXML
    private Button adoptButton;


    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private Button profileButton;

    @FXML
    private Button requestButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button adoptPetButton;
    @FXML
    private ImageView petImage;
    @FXML
    private Label speciesLabel;
    @FXML
    private Label breedLabel;

    @FXML
    private Label healthStatusLabel;

    @FXML
    private Label idLabel;
    @FXML
    private Label ageLabel;

    @FXML
    private GridPane gridPane;

    private petListener PetListener;



    ArrayList<Node> adminNodes = new ArrayList<>();
    ArrayList<Node> adoptNodes = new ArrayList<>();
    int currentMenu = 0; //Swaps between different buttons in the program, 0,1,2,3 for Profile, Adopt, Request, History respectively.


    public ProgramView() {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 1;



        try {


            for (int i = 0; i < Main.allPets.size(); i++) {

                PetListener = new petListener() {
                    @Override
                    public void onClickPet(Pet pet) {
                        setPetData(pet);
                    }
                };

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/pet-post.fxml"));
                AnchorPane anchorPostPane = fxmlLoader.load();

                petPostController petController = fxmlLoader.getController();
                petController.setPostData(Main.allPets.get(i), PetListener);

                if (column == 3)
                {
                    row += 1;
                    column = 0;
                }
                GridPane.setMargin(anchorPostPane, new Insets(10, 10 , 10, 10 ));

                gridPane.add(anchorPostPane, column++, row);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_PREF_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_PREF_SIZE);





            }

        } catch (IOException e) {
            System.out.println("File is gone lmao");
        } catch (NullPointerException ne) {
            System.out.println("Couldn't find the file");

        }
    }


    public void setPetData(Pet pet)
    {
        nameLabel.setText("Name: " + pet.getName());
        ageLabel.setText("Age: "+ pet.getAge());
        healthStatusLabel.setText("Health Status:" + pet.getHealthStatus());
        breedLabel.setText("Breed: " + pet.getBreed());
        speciesLabel.setText("Species: " + pet.getSpecies());
     }


    @FXML
    public void onReturn(ActionEvent e) throws Exception {
        Main m = new Main();
        currentMenu = 0;
        m.changeScene("login-view.fxml");
    }

    public void ChooseMenu() {

        switch (currentMenu) {
            case 0: {

            } // Add nodes for profile Menu
            case 1: {


            } // Add nodes for Adopt Menu\
            case 2: {
            } // Add nodes for Requests menu
            case 3: {
            } // Add nodes for History menu
            default: {
                break;
            }
        }
    }

    @FXML
    void onAdopt(ActionEvent event) {
        currentMenu = 0;
        ChooseMenu();
    }

    @FXML
    void onHistory(ActionEvent event) {
        currentMenu = 1;
        ChooseMenu();
    }

    @FXML
    void onProfile(ActionEvent event) {

    }

    @FXML
    void onRequest(ActionEvent event) {

    }


    @FXML
    void onAdoptPet(ActionEvent event)
    {

    }

    @FXML
    void onHomeView(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/FXML/login-view.fxml");
    }
}







