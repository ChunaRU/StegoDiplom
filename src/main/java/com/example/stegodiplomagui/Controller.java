package com.example.stegodiplomagui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public Button searchButton;
    @FXML
    private Button checkAuthenticityButton;

    @FXML
    private Button projectCopyrightButton;

    private boolean isOpenWindow = false;

    @FXML
    void initialize() {
        projectCopyrightButton.setOnAction(event -> {
            openProjectCopyrightWindow();
        });

        checkAuthenticityButton.setOnAction(event -> {
            openCheckAuthenticityWindow();
        });

        searchButton.setOnAction(event -> {
            openSearchWindow();
        });

    }

    private void openProjectCopyrightWindow() {
        projectCopyrightButton.getScene().getWindow();
        openWindow("projectCopyrightView.fxml");
    }

    private void openCheckAuthenticityWindow() {
        checkAuthenticityButton.getScene().getWindow();
        openWindow("checkAuthenticityView.fxml");

    }
    private void openSearchWindow() {
        searchButton.getScene().getWindow();
        openWindow("searchView.fxml");
    }


    private void openWindow(String name){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(name));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
        isOpenWindow = true;
    }

}
