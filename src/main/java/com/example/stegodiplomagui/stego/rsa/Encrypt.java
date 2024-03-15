package com.example.stegodiplomagui.stego.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    public Encrypt() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    private final Cipher cipher = Cipher.getInstance("RSA");

    public String encryptHash(String hash, Key privateKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(HexToByteArray.hexToBytes(hash));
        return ByteArrayToHex.bytesToHex(bytes);
    }
}


