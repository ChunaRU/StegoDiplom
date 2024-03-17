package com.example.stegodiplomagui;
import com.example.stegodiplomagui.Search.*;

import com.example.stegodiplomagui.digital_electronic_signature.DES;
import com.example.stegodiplomagui.stego.Coding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchController {

    @FXML
    private TextField bookPath; // путь к файлу tmp

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



    public SearchController() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    @FXML
    void initialize() {
        checkWatermarkButton.setOnAction(event -> {
            try {
                checkWatermark();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        checkDesButton.setOnAction(event -> {
            try {
                Check.checkDesButtonAction(bookPath.getText().trim(), publicKeyPath.getText().trim(), desPath.getText().trim());
            } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        });

    }

    List<String> linkList;
    List<String> stolen = new ArrayList<>();;
    private void same(){
        if(search.getText() == null || format.getText() == null)
            System.err.println("Некоркетные данные");

        try {
            linkList = GoogleSearch.getLinks(search.getText(),requiredSearch.getText(),format.getText());
        } catch (IOException e) {
            System.err.println(e.getMessage());


            throw new RuntimeException(e);
        }


    }

    private void checkWatermark() throws IOException {
        int maxTest = 10;

        same();
        System.out.println("ожидайте");

        int testCounter = 0; //счетчик для тестов
        for (String s : linkList) {

            File fileDel = new File("d:/" + "tmp" + "." + format.getText());
            if(fileDel.exists()) {
                System.out.println("DELETE");
               GoogleSearch.delete(fileDel);
            }

            testCounter++;
            if(testCounter > maxTest) break;

            System.out.print("Скачиваение файла" + "  --  ");

                File file = GoogleSearch.download(s, format.getText());
                System.out.print("Проверка файла" + "  --  ");

                if (Check.checkDigitalWatermark(file)) {
                    stolen.add(s);
                    System.err.print("Файл был украден"  + "  --  ");
                }
                System.out.println("Удаление.");
               GoogleSearch.delete(file);

        }

        System.out.println("Украденые файлы: ");
        if(stolen.size() != 0){
            for (String s : stolen) {
                System.out.println(s);
            }
        } else System.out.println("Отсутствуют");
    }




}
