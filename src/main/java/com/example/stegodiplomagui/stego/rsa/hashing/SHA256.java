package com.example.stegodiplomagui.stego.rsa.hashing;


import com.example.stegodiplomagui.stego.rsa.ByteArrayToHex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static String hash(byte[] byteArray) throws NoSuchAlgorithmException {
        return ByteArrayToHex.bytesToHex(MessageDigest.getInstance("SHA256").digest(byteArray));
    }
}
