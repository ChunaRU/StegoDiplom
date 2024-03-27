package com.example.stegodiplomagui;
import com.example.stegodiplomagui.Search.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SearchController {

    @FXML
    private Button checkDesButton;

    @FXML
    private TextField desPath;

    @FXML
    private Button checkWatermarkButton;

    @FXML
    private TextField publicKeyPath;

    @FXML
    private TextField search;
    @FXML
    private TextField requiredSearch;

    @FXML
    private TextField format;



    public SearchController() {
    }

    @FXML
    void initialize() {
        checkWatermarkButton.setOnAction(event -> {
            try {
               Check.check(search,format,requiredSearch,null,null);
            } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new RuntimeException(e);
            }

        });

        checkDesButton.setOnAction(event -> {
            if(publicKeyPath != null && desPath != null) {
                try {
                    Check.check(search,format,requiredSearch, publicKeyPath.getText().trim(), desPath.getText().trim());
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else System.err.println("Пустые значения");
        });

    }








}
