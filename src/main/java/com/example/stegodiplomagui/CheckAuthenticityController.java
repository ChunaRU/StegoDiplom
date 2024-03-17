package com.example.stegodiplomagui;

import com.example.stegodiplomagui.digital_electronic_signature.DES;
import com.example.stegodiplomagui.stego.Coding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CheckAuthenticityController {

    private final Coding coding = new Coding();
    private final DES des = new DES();

    @FXML
    private TextField bookPath;

    @FXML
    private Button checkDesButton;

    @FXML
    private TextField desPath;

    @FXML
    private Button embedButton;

    @FXML
    private TextField filePath1;

    @FXML
    private TextField publicKeyPath;

    public CheckAuthenticityController() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    @FXML
    void initialize() {
        embedButton.setOnAction(event -> {
            embedButtonAction(filePath1.getText());
        });

        checkDesButton.setOnAction(event -> {
            checkDesButtonAction(bookPath.getText().trim(), publicKeyPath.getText().trim(), desPath.getText().trim());
        });

    }

    private void embedButtonAction(String bookPath) {
        try {
            System.out.println("ожидайте");
            coding.decryption(bookPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkDesButtonAction(String bookPathFile, String publicKeyPathFile, String desPathFile) {
        try {
            des.checkDigitalElectronicSignature(bookPathFile, publicKeyPathFile, desPathFile);
        } catch (IOException | NoSuchAlgorithmException | ClassNotFoundException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
