package com.example.stegodiplomagui.Search;

import com.example.stegodiplomagui.Constant;
import com.example.stegodiplomagui.digital_electronic_signature.DES;
import com.example.stegodiplomagui.stego.Coding;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


import javafx.scene.control.TextField;


public class Check {
    private static boolean checkDigitalWatermark(File file) throws IOException {
        Coding coding = new Coding();

        coding.decryption(file.getPath());

        return coding.decryption(file.getPath()).equals(Constant.getWatermark());
    }

    private static boolean checkDes(String bookPathFile, String publicKeyPathFile, String desPathFile) throws NoSuchPaddingException, NoSuchAlgorithmException {
        DES des = new DES();
        try {
           return des.checkDigitalElectronicSignature(bookPathFile, publicKeyPathFile, desPathFile);

        } catch (IOException | NoSuchAlgorithmException | ClassNotFoundException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }



    public static void check(TextField search, TextField format, TextField requiredSearch, String publicKeyPathFile, String desPathFile) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException {
        List<String> linkList = null;
        List<String> stolen = new ArrayList<>();

        if (!Objects.equals(search.getText(), "") && !Objects.equals(format.getText(), "")) {

            if(search.getText() == null || format.getText() == null) {
                System.err.println("Некоркетные данные");
            }

            try {
                linkList = GoogleSearch.getLinks(search.getText(),requiredSearch.getText(),format.getText());
            } catch (IOException e) {
            }



            int maxTest = 10;
            int testCounter = 0; //счетчик для тестов

            for (String s : linkList) {

                File fileDel = new File("tmp" + "." + format.getText());
                if(fileDel.exists()) {
                    System.out.println("DELETE");
                    GoogleSearch.delete(fileDel);
                }

                testCounter++;
                if(testCounter > maxTest) break;

                System.out.print("Скачиваение файла" + "  --  ");

                File file = GoogleSearch.download(s, format.getText());
                System.out.print("Проверка файла" + "  --  ");



                if (Check.checkDigitalWatermark(file) || checkDes(file.getPath(),publicKeyPathFile,desPathFile)) {
                      stolen.add(s);
                      System.err.print("Файл был украден"  + "  --  ");
                  }else System.out.print("Файл чист");

                GoogleSearch.delete(file);

            }

            System.out.println("Украденые файлы: ");
            if(!stolen.isEmpty()){
                for (String s : stolen) {
                    System.out.println(s);
                }
            } else System.out.println("Отсутствуют");
        } else System.err.println("Введите данные для поиска");

    }

}
