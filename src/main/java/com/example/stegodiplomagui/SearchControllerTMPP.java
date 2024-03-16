package com.example.stegodiplomagui;

import com.example.stegodiplomagui.Search.GoogleSearch;
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

public class SearchControllerTMPP {

    private final Coding coding = new Coding();
    private final DES des = new DES();

    @FXML
    private TextField bookPath; // путь к файлу tmp

    @FXML
    private Button checkDesButton;

    @FXML
    private TextField desPath;

    @FXML
    private Button embedButton;

    @FXML
    private TextField publicKeyPath;

    @FXML
    private TextField search;
    @FXML
    private TextField requiredSearch;

    @FXML
    private TextField format;



    public SearchControllerTMPP() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    @FXML
    void initialize() {
        embedButton.setOnAction(event -> {
            try {
                embedButtonAction();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        checkDesButton.setOnAction(event -> {
            checkDesButtonAction(bookPath.getText().trim(), publicKeyPath.getText().trim(), desPath.getText().trim());
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

    private void embedButtonAction() throws IOException {
        int maxTest = 10;

        same();
        System.out.println("ожидайте");

        int testCounter = 0; //счетчик для тестов
        for (String s : linkList) {

            File fileDel = new File("d:/" + "tmp" + "." + format.getText());
            if(fileDel.exists()) {
                System.out.println("DELETE");
                delete(fileDel);
            }

            testCounter++;
            if(testCounter > maxTest) break;

            System.out.print("Скачиваение файла" + "  --  ");

                File file = download(s, format.getText());
                System.out.print("Проверка файла" + "  --  ");

                if (checkDigitalWatermark(file)) {
                    stolen.add(s);
                    System.err.print("Файл был украден"  + "  --  ");
                }
                System.out.println("Удаление.");
                delete(file);

        }

        System.out.println("Украденые файлы: ");
        if(stolen.size() != 0){
            for (String s : stolen) {
                System.out.println(s);
            }
        } else System.out.println("Отсутствуют");
    }

    private void checkDesButtonAction(String bookPathFile, String publicKeyPathFile, String desPathFile) {
        try {
            des.checkDigitalElectronicSignature(bookPathFile, publicKeyPathFile, desPathFile);
        } catch (IOException | NoSuchAlgorithmException | ClassNotFoundException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }




    static File download(String strURL, String format) {
        try {
            URL url = new URL(strURL);
            InputStream inputStream = url.openStream();
            Files.copy(inputStream, new File("d:/" + "tmp" + "." + "doc").toPath());

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return new File("d:/" + "tmp" + "." + format);
    }

    static void delete(File file){
        file.delete();
    }
    boolean checkDigitalWatermark(File file) throws IOException {
        coding.decryption(file.getPath());


        byte[] content1 = Files.readAllBytes(Paths.get("decryptionKey.txt"));
        byte[] content2 = Files.readAllBytes(Paths.get("decryption.txt"));

        if(Arrays.equals(content1, content2)){
            return true;
        }
        return false;
    }


}
