package com.example.stegodiplomagui.stego.rsa.model;

import java.io.Serializable;
import java.security.Key;

public class PublicKeyModel implements Serializable {
    private Key publicKey;

    public void setPublicKey(Key publicKey) {
        this.publicKey = publicKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }
}
