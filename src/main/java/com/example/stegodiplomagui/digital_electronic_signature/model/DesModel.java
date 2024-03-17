package com.example.stegodiplomagui.digital_electronic_signature.model;

import java.io.Serializable;

public class DesModel implements Serializable {

    private String digitalElectronicSignature;

    public void setDigitalElectronicSignature(String digitalElectronicSignature) {
        this.digitalElectronicSignature = digitalElectronicSignature;
    }

    public String getDigitalElectronicSignature() {
        return digitalElectronicSignature;
    }
}
