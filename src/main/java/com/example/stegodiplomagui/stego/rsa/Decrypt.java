package com.example.stegodiplomagui.stego.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Decrypt {

    public Decrypt() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    private final Cipher cipher = Cipher.getInstance("RSA");

    public String decryptDES(String des, Key publicKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(HexToByteArray.hexToBytes(des));
        return ByteArrayToHex.bytesToHex(bytes);
    }
}
