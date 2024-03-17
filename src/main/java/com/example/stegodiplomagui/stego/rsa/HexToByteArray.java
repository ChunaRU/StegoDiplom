package com.example.stegodiplomagui.stego.rsa;

public class HexToByteArray {
    public static byte[] hexToBytes(String hexString) {
        int length = hexString.length();

        if (length % 2 != 0) {
            hexString = "0" + hexString;
            length++;
        }

        byte[] byteArray = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            byteArray[i / 2] = (byte) Integer.parseInt(hexString.substring(i, i + 2), 16);
        }

        return byteArray;
    }
}
