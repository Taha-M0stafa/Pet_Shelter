package com.example.GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class reportingUsers {
    @FXML
    private PieChart genderPieChart;

    @FXML
    private BarChart<String, Number> ageBarChart;

    @FXML
    private FlowPane tagCloudPane;
    @FXML
    private BarChart<String, Number> MostAdoptedBarChart;
    @FXML
    private BarChart<String, Number> MostAdoptedTimesNumRequest;


    private static final String JSON_FILE_PATH = "users.json";
    private static final String JSON_FILE_PATH2 = "Requests.json";

    public void initialize() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH)));
            JSONArray users = new JSONArray(jsonString);
            String jsonString2 = new String(Files.readAllBytes(Paths.get(JSON_FILE_PATH2)));
            JSONArray requests = new JSONArray(jsonString2);


            // Populate charts and list
            populateGenderPieChart(users);
            populateAgeBarChart(users);
            populateTagCloud(users);
            populateMostAdoptedBarChart(users);
            populateMostAdoptedTimesNumRequest(requests);

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

    private void populateMostAdoptedBarChart(JSONArray users) {
        Map<String, Integer> petCounts = new HashMap<>(); //O(1) searching (array or list --> O(n)

        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.has("currentPets")) {
                JSONArray currentPets = user.getJSONArray("currentPets");

                for (int j = 0; j < currentPets.length(); j++) {
                    JSONObject pet = currentPets.getJSONObject(j);
                    String petName = pet.getString("species");

                    petCounts.put(petName, petCounts.getOrDefault(petName, 0) + 1); //default val =0
                }
            }
        }
      //counting logic
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Most Owned Pets");
        for (Map.Entry<String, Integer> entry : petCounts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        MostAdoptedBarChart.getData().add(series);
    }


    private void populateMostAdoptedTimesNumRequest(JSONArray requests) {
        Map<String, Integer> adoptionCounts = new HashMap<>();

        // Populate the Map with adoption data
        for (int i = 0; i < requests.length(); i++) {
            JSONObject request = requests.getJSONObject(i);
            String adoptionDateStr = request.getString("adoptionDate");
            adoptionCounts.put(adoptionDateStr, adoptionCounts.getOrDefault(adoptionDateStr, 0) + 1);
        }

        // data holderd
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Adoption Over Time");
        for (Map.Entry<String, Integer> entry : adoptionCounts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        MostAdoptedTimesNumRequest.getData().add(series);


    }


}




