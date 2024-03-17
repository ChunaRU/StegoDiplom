package com.example.stegodiplomagui.stego.rsa;


import com.example.stegodiplomagui.digital_electronic_signature.model.DesModel;
import com.example.stegodiplomagui.stego.rsa.model.PrivateKeyModel;
import com.example.stegodiplomagui.stego.rsa.model.PublicKeyModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;

public class ObjectOutput {
    private final PublicKeyModel publicKeyModel = new PublicKeyModel();
    private final PrivateKeyModel privateKeyModel = new PrivateKeyModel();
    private final DesModel desModel = new DesModel();

    public void savingKeys(Key publicKey, Key privateKey) throws IOException {
        publicKeyModel.setPublicKey(publicKey);
        privateKeyModel.setPrivateKey(privateKey);

        outputStream(publicKeyModel, "publicKey.key");
        outputStream(privateKeyModel, "privateKey.key");
    }

    public void savingDES(String des) throws IOException {
        desModel.setDigitalElectronicSignature(des);
        outputStream(desModel, "digitalElectronicSignature.des");
    }

    private void outputStream(Object object, String objectName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(objectName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }
}
