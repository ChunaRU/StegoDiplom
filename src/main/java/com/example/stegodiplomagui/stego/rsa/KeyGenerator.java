package com.example.stegodiplomagui.stego.rsa;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {

    public KeyGenerator() throws NoSuchAlgorithmException {
    }

    private Key publicKey;
    private Key privateKey;
    private final KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");

    public void generateKeys() {
        pairGenerator.initialize(4096);
        KeyPair keyPair = pairGenerator.generateKeyPair();

        setPublicKey(keyPair.getPublic());
        setPrivateKey(keyPair.getPrivate());
    }

    public void setPublicKey(Key publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(Key privateKey) {
        this.privateKey = privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }

    public Key getPrivateKey() {
        return privateKey;
    }
}
