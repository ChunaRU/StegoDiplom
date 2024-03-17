package com.example.stegodiplomagui.Search;

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
import java.util.Arrays;

public class Check {
    public static boolean checkDigitalWatermark(File file) throws IOException {
        Coding coding = new Coding();

        coding.decryption(file.getPath());

        byte[] content1 = Files.readAllBytes(Paths.get("decryptionKey.txt"));
        byte[] content2 = Files.readAllBytes(Paths.get("decryption.txt"));

        return Arrays.equals(content1, content2);
    }

    public static void checkDesButtonAction(String bookPathFile, String publicKeyPathFile, String desPathFile) throws NoSuchPaddingException, NoSuchAlgorithmException {
        DES des = new DES();
        try {
            des.checkDigitalElectronicSignature(bookPathFile, publicKeyPathFile, desPathFile);
        } catch (IOException | NoSuchAlgorithmException | ClassNotFoundException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
