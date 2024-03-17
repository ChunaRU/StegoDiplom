package com.example.stegodiplomagui.stego.rsa.model;

import java.io.Serializable;
import java.security.Key;

public class PrivateKeyModel implements Serializable {
    private Key privateKey;
    public void setPrivateKey(Key privateKey) {
        this.privateKey = privateKey;
    }

    public Key getPrivateKey() {
        return privateKey;
    }
}
