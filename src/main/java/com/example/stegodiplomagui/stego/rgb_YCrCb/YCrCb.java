package com.example.stegodiplomagui.stego.rgb_YCrCb;

public class YCrCb {
    private final double Y;
    private final double Cr;
    private final double Cb;

    public YCrCb(double Y, double Cr, double Cb) {
        this.Y = Y;
        this.Cr = Cr;
        this.Cb = Cb;
    }
    public double getY() {
        return Y;
    }
    public double getCr() {
        return Cr;
    }
    public double getCb() {
        return Cb;
    }
}
