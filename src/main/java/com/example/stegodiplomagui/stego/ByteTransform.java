package com.example.stegodiplomagui.stego;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ByteTransform {
    private final Loader loader = new Loader();

    public byte[] fileToByteArray(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(loader.loadFile(filePath).getPath()));
    }

    public String[] byteArrayToStringBitsArray(String watermark) {
        byte[] byteArray = watermark.getBytes(StandardCharsets.UTF_8);
        String[] bitArray = new String[byteArray.length];

        for (int i = 0; i < byteArray.length; i++)
            bitArray[i] = String.format("%8s", Integer.toBinaryString(byteArray[i] & 0xFF)).replace(" ", "0");

        return bitArray;
    }

    public char[] getBitsCharArray(String watermark) {
        String[] bitsArray = byteArrayToStringBitsArray(watermark);
        char[] bitsCharArray = new char[bitsArray.length * 8];

        int k = 0;
        for (String str : bitsArray) {
            char[] tmp = str.toCharArray();
            for (char ch : tmp) {
                bitsCharArray[k] = ch;
                k++;
            }
        }
        return bitsCharArray;
    }

    public byte[] bitsArrayToByteArray(String[] bitsArray) {
        byte[] byteArray = new byte[bitsArray.length];

        for (int i = 0; i < bitsArray.length; i++)
            byteArray[i] = (byte) Integer.parseInt(bitsArray[i], 2);

        return byteArray;
    }

    public void byteArrayToFile(byte[] byteArray, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(byteArray);
        out.close();
    }
}
