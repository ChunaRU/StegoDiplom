package com.example.stegodiplomagui.stego.rgb_YCrCb;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RGB_YCrCb_Matrix {
    RGB_To_YCrCb rgb_to_yCrCb = new RGB_To_YCrCb();
    YCrCb_To_RGB yCrCb_to_rgb = new YCrCb_To_RGB();

    public Color[][] createRGBMatrix(BufferedImage image, int imageHeight, int imageWidth) {
        Color[][] matrix = new Color[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                matrix[i][j] = new Color(image.getRGB(j, i));
            }
        }

        return matrix;
    }

    public YCrCb[][] createYCrCbMatrix(Color[][] color, int imageHeight, int imageWidth) {
        YCrCb[][] matrix = new YCrCb[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                matrix[i][j] = new YCrCb(rgb_to_yCrCb.getY(color[i][j]), rgb_to_yCrCb.getCr(color[i][j]), rgb_to_yCrCb.getCb(color[i][j]));
            }
        }

        return matrix;
    }

    public Color[][] YCrCb_To_RGBMatrix(YCrCb[][] color,int imageHeight, int imageWidth) {
        Color[][] matrix = new Color[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                int newRed = yCrCb_to_rgb.getRed(color[i][j]);
                int newGreen = yCrCb_to_rgb.getGreen(color[i][j]);
                int newBlue = yCrCb_to_rgb.getBlue(color[i][j]);

                matrix[i][j] = new Color(newRed, newGreen, newBlue);
            }
        }

        return matrix;
    }

    public double[][] createYMatrix(YCrCb[][] color, int imageHeight, int imageWidth) {
        double[][] matrix = new double[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                matrix[i][j] = color[i][j].getY();
            }
        }

        return matrix;
    }

    public YCrCb[][] YMatrix_To_YCrCbMatrix(YCrCb[][] color, double[][] yMatrix, int imageHeight, int imageWidth) {
        YCrCb[][] tmpMatrix = new YCrCb[imageHeight][imageWidth];

        for (int i = 0; i < imageHeight; i++) {
            for (int j = 0; j < imageWidth; j++) {
                tmpMatrix[i][j] = new YCrCb(yMatrix[i][j], color[i][j].getCr(), color[i][j].getCb());
            }
        }

        return tmpMatrix;
    }
}
