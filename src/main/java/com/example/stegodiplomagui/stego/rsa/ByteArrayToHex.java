package com.example.stegodiplomagui.stego.rsa;

import java.math.BigInteger;

public class ByteArrayToHex {
    public static String bytesToHex(byte[] byteArray) {
        BigInteger bigInteger = new BigInteger(1, byteArray);
        String hexString = bigInteger.toString(16);
        int paddingLength = (byteArray.length * 2) - hexString.length();

        if (paddingLength > 0) {
            String padding = String.format("%0" + paddingLength + "d", 0);
            hexString = padding + hexString;
        }
        return hexString;
    }
}
