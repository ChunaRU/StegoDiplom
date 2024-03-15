package com.example.stegodiplomagui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button checkAuthenticityButton;

    @FXML
    private Button projectCopyrightButton;
    @FXML
    private Button searchButton;

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

    private void openSearchWindow() {
        searchButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchView.fxml"));

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
    }

    private void openProjectCopyrightWindow() {
        projectCopyrightButton.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("projectCopyrightView.fxml"));

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
    }

    private void openCheckAuthenticityWindow() {
        checkAuthenticityButton.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("checkAuthenticityView.fxml"));

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
