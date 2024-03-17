package com.example.stegodiplomagui.stego.rgb_YCrCb;

public class YCrCb_To_RGB {
    public int getRed(YCrCb yCrCb) {
        int tmp = (int) Math.round(yCrCb.getY() + 1.402 * (yCrCb.getCr() - 128));

        if (tmp > 255)
            tmp = 255;
        else if (tmp < 0)
            tmp = 0;

        return tmp;
    }

    public int getGreen(YCrCb yCrCb) {
        int tmp = (int) Math.round(yCrCb.getY() - 0.34414 * (yCrCb.getCb() - 128) - 0.71414 * (yCrCb.getCr() - 128));

        if (tmp > 255)
            tmp = 255;
        else if (tmp < 0)
            tmp = 0;

        return tmp;
    }

    public int getBlue(YCrCb yCrCb) {
        int tmp = (int) Math.round(yCrCb.getY() + 1.772 * (yCrCb.getCb() - 128));

        if (tmp > 255)
            tmp = 255;
        else if (tmp < 0)
            tmp = 0;

        return tmp;
    }
}
