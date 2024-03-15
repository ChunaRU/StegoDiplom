package com.example.stegodiplomagui;

import com.example.stegodiplomagui.digital_electronic_signature.DES;
import com.example.stegodiplomagui.stego.Coding;
import com.example.stegodiplomagui.zip.FileFormatExtractor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ProjectCopyrightController {
    private final Coding coding = new Coding();
    private final DES des = new DES();

    @FXML
    private Button createButton;

    @FXML
    private Button embedButton;

    @FXML
    private TextField filePath;

    @FXML
    private Label label;


    public ProjectCopyrightController() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    @FXML
    void initialize() {
        embedButton.setOnAction(event -> {
            embedButtonAction(filePath.getText());
        });

        createButton.setOnAction(event -> {
            createButtonAction(filePath.getText());
        });
    }

    private void embedButtonAction(String bookPath) throws RuntimeException {
        try {
            if (bookPath.isEmpty())
                throw new RuntimeException("ВВЕДИТЕ ПУТЬ!!!");
//            else if (!bookPath.isEmpty() || !FileFormatExtractor.getFileFormat(bookPath).equals(".epub"))
//                throw new RuntimeException("НЕВЕРНЫЙ ПУТЬ К ФАЙЛУ ИЛИ НЕВЕРНЫЙ ФОРМАТ ФАЙЛА");
            else coding.encryption(bookPath);
        } catch (IOException s) {
        }
    }

    private void createButtonAction(String bookPath) {
        try {
            if (bookPath.isEmpty())
                throw new RuntimeException("ВВЕДИТЕ ПУТЬ!!!");
//            else if (!bookPath.isEmpty() || FileFormatExtractor.getFileFormat(bookPath).equals(".epub"))
//                throw new RuntimeException("НЕВЕРНЫЙ ПУТЬ К ФАЙЛУ ИЛИ НЕВЕРНЫЙ ФОРМАТ ФАЙЛА");
            else des.getDigitalElectronicSignature(bookPath);

        } catch (IOException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}


