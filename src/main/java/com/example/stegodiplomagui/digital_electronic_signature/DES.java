package com.example.stegodiplomagui.digital_electronic_signature;

import com.example.stegodiplomagui.stego.ByteTransform;
import com.example.stegodiplomagui.stego.rsa.*;
import com.example.stegodiplomagui.stego.rsa.hashing.SHA256;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class DES {

    public DES() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    private final ByteTransform byteTransform = new ByteTransform();
    private final Encrypt encrypt = new Encrypt();
    private final Decrypt decrypt = new Decrypt();
    private final KeyGenerator keyGenerator = new KeyGenerator();
    private final ObjectOutput objectOutput = new ObjectOutput();
    private final ObjectInput objectInput = new ObjectInput();

    public void getDigitalElectronicSignature(String filePath) throws IOException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        keyGenerator.generateKeys();
        objectOutput.savingKeys(keyGenerator.getPublicKey(), keyGenerator.getPrivateKey());
        objectOutput.savingDES(des(filePath, keyGenerator.getPrivateKey()));
        System.out.println(
                "Цифровая электронная подпись успешно создана" + "\n" +
                "____________________________________________");
    }

    public boolean checkDigitalElectronicSignature(String filePath, String publicKeyPath, String desPath) throws IOException, NoSuchAlgorithmException, ClassNotFoundException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String desHash = decrypt.decryptDES(objectInput.inputDES(desPath), objectInput.inputPublicKey(publicKeyPath));
        String fileHash = SHA256.hash(getBytes(filePath));
        if (desHash.equals(fileHash)){
            System.out.println(
                    "Электронное учебное пособие является подлинным" + "\n" +
                            "______________________________________________");
            return true;
        }
        else {
            System.out.println(
                    "Электронное учебное пособие не является подлинным" + "\n" +
                            "_________________________________________________");
            return false;
        }
    }

    private String des(String filePath, Key privateKey) throws IOException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        return encrypt.encryptHash(SHA256.hash(getBytes(filePath)), privateKey);
    }

    private byte[] getBytes(String filePath) throws IOException {
        return byteTransform.fileToByteArray(filePath);
    }
}
