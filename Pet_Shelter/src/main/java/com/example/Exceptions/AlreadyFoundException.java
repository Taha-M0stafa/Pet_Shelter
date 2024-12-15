package com.example.Exceptions;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlreadyFoundException extends RuntimeException {
    public AlreadyFoundException(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        stage.showAndWait();
    }
}
