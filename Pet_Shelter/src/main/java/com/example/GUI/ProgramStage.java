package com.example.GUI;

import com.example.pet_shelter.AdoptionRequest;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.Pet;
import com.example.pet_shelter.Shelter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProgramStage extends AnchorPane implements Initializable {

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

    @FXML
    private VBox adoptVBox;

    public Pet chosenPet;

    @FXML
    private Button shelterButton;

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private HBox swapHBox;
    private petListener PetListener;

    public static Shelter chosenShelter;


    //Nodes used in the  Admin pane

    @FXML
    private HBox adminHBox;


    //List of nodes to swap betweeen scenes



    ArrayList<Node> adminNodes = new ArrayList<>();
    ArrayList<Node> adoptNodes = new ArrayList<>();

    int currentMenu = 0; //Swaps between different buttons in the program, 0,1,2,3 for Profile, Adopt, Request, History respectively.


    public ProgramStage() {

    }

    @FXML
    void onReporting() throws IOException{
        addNewStage("/FXML/reportingUsers.fxml", "Reporting");
        System.out.println("i'm here");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Store all scene nodes in their respective arrayLists.
        adoptNodes.add(adoptVBox);
        adoptNodes.add(leftAnchorPane);
        adoptNodes.add(shelterButton);


        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(swapHBox);


        adoptNodes.forEach(child -> {child.setVisible(true);});

        mainAnchorPane.getChildren().addAll(adoptNodes);
        showPetPosts();

        //Remove Admin button if the user is not an admin

        if(/*!User.loggedInUser.getUserRole().equals("admin"*/ false){
            swapHBox.getChildren().remove(adminButton);
        }


    }//End of logic;


    public void setPetData(Pet pet)
    {
        nameLabel.setText("Name: " + pet.getName());
        ageLabel.setText("Age: "+ pet.getAge());
        healthStatusLabel.setText("Health Status:" + pet.getHealthStatus());
        breedLabel.setText("Breed: " + pet.getBreed());
        speciesLabel.setText("Species: " + pet.getSpecies());
        petImage.setImage(pet.getPetImage());
     }



    public void ChooseMenu() {


        mainAnchorPane.getChildren().clear();

        mainAnchorPane.getChildren().add(swapHBox);


        switch (currentMenu) {

            //Add nodes for the adoption view menu
            case 0: {
                mainAnchorPane.getChildren().addAll(adoptNodes);
                break;
            }
            // Add nodes for profile Menu
            case 1: {

                break;
            }
            // Add nodes for Adopt Menu
            case 2: {


                break;
            }
            // Add nodes for Requests menu
            case 3: {
                mainAnchorPane.getChildren().add(adminHBox);
                break;
            }
            // Add nodes for History menu
            default: {
                break;
            }
        }
    }

    @FXML
    void onAdopt(ActionEvent event) {
        currentMenu = 0;
        showPetPosts();
        ChooseMenu();

        System.out.println("I was clicked but not swapped");
    }

    @FXML
    void onHistory(ActionEvent event) {
        currentMenu = 1;
        ChooseMenu();
    }

    @FXML
    void onProfile(ActionEvent event) {
        currentMenu = 2;
        ChooseMenu();
    }

    @FXML
    void onRequest(ActionEvent event) {
        currentMenu = 3;
        ChooseMenu();
    }


    @FXML
    void onAdoptPet(ActionEvent event)
    {
        AdoptionRequest.adopt(chosenPet);

    }

    @FXML
    void onAdminButton(ActionEvent event)
    {
        currentMenu = 3;
        ChooseMenu();
    }

    @FXML
    void onHomeView(MouseEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("/FXML/login-view.fxml");
    }

    @FXML
    void onModifyUser() throws IOException {
        addNewStage("/FXML/modify-user.fxml", "Modify Users");
    }

    @FXML
    void onModifyPet() throws IOException{
       addNewStage("/FXML/modify-pet.fxml", "Modify Pet");
    }

    @FXML
    void onModifyShelter() throws IOException{
        addNewStage("/FXML/modify-shelter.fxml", "Modify Shelter");
    }

    private void addNewStage(String fxml, String title) throws IOException {
        Stage stage = new Stage();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.sizeToScene();
        FXMLLoader fxmlLoader = new FXMLLoader(ProgramStage.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.toFront();
        stage.setAlwaysOnTop(true);
        stage.requestFocus();
        stage.showAndWait();
    }

    private void showPetPosts()
    {
        //Add all pet posts to the main page on Start-up

        int column = 0;
        int row = 1;

        if(!chosenShelter.getPets().isEmpty())
        {
            PetListener = new petListener() {
                @Override
                public void onClickPet(Pet pet) {
                    setPetData(pet);
                    chosenPet=pet;
                }
            };
        }

        try {
            for (int i = 0; i < chosenShelter.getPets().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/pet-post.fxml"));
                AnchorPane anchorPostPane = fxmlLoader.load();
                petPostController petController = fxmlLoader.getController();
                petController.setPostData(chosenShelter.getPets().get(i), PetListener);
                if (column == 2)
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


    @FXML
    void onShelter() throws IOException
    {
        Main m = new Main();
        m.changeScene("/FXML/Shelter-view.fxml");
    }


    @FXML
    void OnRequest() throws IOException {
        addNewStage("/FXML/Request stage.fxml","RequestStage");
    }


}








