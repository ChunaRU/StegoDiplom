package com.example.stegodiplomagui.stego.rgb_YCrCb;

import java.awt.*;

public class RGB_To_YCrCb {
    public int getY(Color color) {
        return (int) Math.round (0.299 * (double)color.getRed() + 0.587 * (double)color.getGreen() + 0.114 * (double)color.getBlue());
    }

    public int getCr(Color color) {
        return (int) Math.round (128 + (0.5 * (double)color.getRed() - 0.419 * (double)color.getGreen() - 0.081 * (double)color.getBlue()));
    }

    public int getCb(Color color) {
        return (int) Math.round (128 + (-0.169 * (double)color.getRed() - 0.331 * (double)color.getGreen() + 0.5 * (double)color.getBlue()));
    }
}
