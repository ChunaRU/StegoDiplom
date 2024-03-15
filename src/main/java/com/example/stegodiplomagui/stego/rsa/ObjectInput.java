package com.example.stegodiplomagui.stego.rsa;

import com.example.stegodiplomagui.digital_electronic_signature.model.DesModel;
import com.example.stegodiplomagui.stego.rsa.model.PublicKeyModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;

public class ObjectInput {

    public Key inputPublicKey(String publicKeyPath) throws IOException, ClassNotFoundException {
        return ((PublicKeyModel) inputStream(publicKeyPath).readObject()).getPublicKey();
    }

    public String inputDES(String desPath) throws IOException, ClassNotFoundException {
        return ((DesModel) inputStream(desPath).readObject()).getDigitalElectronicSignature();
    }

    private ObjectInputStream inputStream(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        return new ObjectInputStream(fileInputStream);
    }
}
