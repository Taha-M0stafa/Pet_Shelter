package com.example.GUI;
import com.example.pet_shelter.Main;
import com.example.pet_shelter.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProgramView implements Initializable {

    @FXML
    BorderPane borderPane;
    @FXML
    Button adoptButton, profileButton, adminButton, returnButton;
    @FXML
    AnchorPane topAnchorPane, leftAnchorPane;








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Node> adminNodes = new ArrayList<Node>();
        adminNodes.add(adminButton);

        if(!User.loggedInUser.getUserRole().equals("admin"))
            topAnchorPane.getChildren().remove(adminButton);
    }
    @FXML
    public void onReturn(ActionEvent e) throws Exception
    {
        Main m = new Main();
        m.changeScene("hello-view.fxml");
    }

}
