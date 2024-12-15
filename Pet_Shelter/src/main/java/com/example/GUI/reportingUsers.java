package com.example.GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class reportingUsers {
    @FXML
    private PieChart genderPieChart;

    @FXML
    private BarChart<String, Number> ageBarChart;

    @FXML
    private FlowPane tagCloudPane;


    private static final String JSON_FILE_PATH = "users.json";

    public void initialize() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            JSONArray users = new JSONArray(jsonString);

            // Populate charts and list
            populateGenderPieChart(users);
            populateAgeBarChart(users);
            populateTagCloud(users);

        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    private void populateGenderPieChart(JSONArray users) {
        int maleCount = 0;
        int femaleCount = 0;

        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            String gender = user.getString("gender");

            if ("male".equalsIgnoreCase(gender)) {
                maleCount++;
            } else if ("female".equalsIgnoreCase(gender)) {
                femaleCount++;
            }
        }

        genderPieChart.getData().add(new PieChart.Data("Male", maleCount));
        genderPieChart.getData().add(new PieChart.Data("Female", femaleCount));
    }

    private void populateAgeBarChart(JSONArray users) {
        XYChart.Series<String, Number> ageSeries = new XYChart.Series<>();
        ageSeries.setName("Age Groups");

        int[] ageGroups = new int[5]; // 0-19, 20-29, 30-39, 40-49, 50+
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            int age = user.getInt("age");

            if (age < 20) {
                ageGroups[0]++;
            } else if (age < 30) {
                ageGroups[1]++;
            } else if (age < 40) {
                ageGroups[2]++;
            } else if (age < 50) {
                ageGroups[3]++;
            } else {
                ageGroups[4]++;
            }
        }

        ageSeries.getData().add(new XYChart.Data<>("0-19", ageGroups[0]));
        ageSeries.getData().add(new XYChart.Data<>("20-29", ageGroups[1]));
        ageSeries.getData().add(new XYChart.Data<>("30-39", ageGroups[2]));
        ageSeries.getData().add(new XYChart.Data<>("40-49", ageGroups[3]));
        ageSeries.getData().add(new XYChart.Data<>("50+", ageGroups[4]));

        ageBarChart.getData().add(ageSeries);
    }

    private void populateTagCloud(JSONArray users) {
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            String address = user.getString("address");
            Label tag = new Label(address);
            tag.setStyle("-fx-background-color: #ee730e; -fx-padding: 5px; -fx-border-radius: 5px;");
            tagCloudPane.getChildren().add(tag);
        }
    }
}




