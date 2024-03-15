package com.example.stegodiplomagui.stego;

import com.example.stegodiplomagui.stego.rgb_YCrCb.RGB_YCrCb_Matrix;
import com.example.stegodiplomagui.stego.rgb_YCrCb.YCrCb;
import com.example.stegodiplomagui.stego.wavelet.WaveletTransform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.example.stegodiplomagui.zip.FileDeleter.deleteFile;
import static com.example.stegodiplomagui.zip.FileNameExtractor.getFileName;
import static com.example.stegodiplomagui.zip.ImageFinder.findImageFilePaths;
import static com.example.stegodiplomagui.zip.ReplaceFileInZipExample.replaceFileInZipExample;
import static com.example.stegodiplomagui.zip.ZipImageReader.readImageInZip;


public class Coding {
    private final RGB_YCrCb_Matrix rgb_yCrCb_matrix = new RGB_YCrCb_Matrix();
    private final ByteTransform byteTransform = new ByteTransform();
    private final WaveletTransform waveletTransform = new WaveletTransform();
    private final String watermark = "psu.by";
    private final int countBits = watermark.length() * 8;

    public void encryption(String zipFilePath) throws IOException {
        System.out.println("Идет процесс встраивания цифрового водяного знака...");

        List<String> imagePaths = findImageFilePaths(zipFilePath);

        Collections.sort(imagePaths);
        for (String imagePath : imagePaths) {
            BufferedImage image = readImageInZip(zipFilePath, imagePath);

            String fileName = getFileName(imagePath);

            int imageHeight = image.getHeight();
            int imageWidth = image.getWidth();

            char[] bitsCharArray = byteTransform.getBitsCharArray(watermark);

            Color[][] rgbMatrix = rgb_yCrCb_matrix.createRGBMatrix(image, imageHeight, imageWidth);
            YCrCb[][] yCrCbMatrix = rgb_yCrCb_matrix.createYCrCbMatrix(rgbMatrix, imageHeight, imageWidth);
            double[][] yMatrix = rgb_yCrCb_matrix.createYMatrix(yCrCbMatrix, imageHeight, imageWidth);
            Matrix matY = new Matrix(imageHeight, imageWidth, yMatrix);
            ArrayList<double[][]> arrayOf8x8TransformedMatrix = waveletTransform.arrayOfMatrixDirectTransform(matY.getArrayOf8x8Matrix(yMatrix, imageHeight, imageWidth));

            for (int i = 0; i < countBits; i++) {
                double[][] tmpMatrix = arrayOf8x8TransformedMatrix.get(i);
                double p = 1;
                switch (bitsCharArray[i]) {
                    case '1': {
                        if ((tmpMatrix[0][0] - tmpMatrix[0][1]) < -p) {
                            tmpMatrix[0][0] += 1;
                            tmpMatrix[0][1] += 1;
                            arrayOf8x8TransformedMatrix.set(i, tmpMatrix);
                            break;
                        } else if ((tmpMatrix[0][0] - tmpMatrix[0][1]) >= -p) {
                            while ((tmpMatrix[0][0] - tmpMatrix[0][1]) > -p) {
                                tmpMatrix[0][0] -= 1;
                                tmpMatrix[0][1] += 1;
                            }
                            arrayOf8x8TransformedMatrix.set(i, tmpMatrix);
                            break;
                        }
                    }
                    case '0': {
                        if ((tmpMatrix[0][0] - tmpMatrix[0][1]) > p) {
                            tmpMatrix[0][0] += 1;
                            tmpMatrix[0][1] += 1;
                            arrayOf8x8TransformedMatrix.set(i, tmpMatrix);
                            break;
                        } else if ((tmpMatrix[0][0] - tmpMatrix[0][1]) <= p) {
                            while ((tmpMatrix[0][0] - tmpMatrix[0][1]) < p) {
                                tmpMatrix[0][0] += 1;
                                tmpMatrix[0][1] -= 1;
                            }
                            arrayOf8x8TransformedMatrix.set(i, tmpMatrix);
                            break;
                        }
                    }
                }
            }

            ArrayList<double[][]> arrayOfMatrixInverseTransform = waveletTransform.arrayOfMatrixInverseTransform(arrayOf8x8TransformedMatrix);
            YCrCb[][] YCrCbFinalMatrix = rgb_yCrCb_matrix.YMatrix_To_YCrCbMatrix(yCrCbMatrix, matY.getBigMatrix(arrayOfMatrixInverseTransform, imageHeight, imageWidth), imageHeight, imageWidth);

            Color[][] RGBFinalMatrix = rgb_yCrCb_matrix.YCrCb_To_RGBMatrix(YCrCbFinalMatrix, imageHeight, imageWidth);

            for (int l = 0; l < imageHeight / 8 * 8; l++) {
                for (int j = 0; j < imageWidth / 8 * 8; j++) {
                    Color newColor = new Color(RGBFinalMatrix[l][j].getRed(), RGBFinalMatrix[l][j].getGreen(), RGBFinalMatrix[l][j].getBlue());
                    image.setRGB(j, l, newColor.getRGB());
                }
            }

            File output = new File(fileName);
            ImageIO.write(image, "png", output);
            System.out.println(".");

            replaceFileInZipExample(fileName, zipFilePath, imagePath);
            deleteFile(fileName);
        }

        System.out.println(
                "Цифровой водяной знак успешно встроен!" + "\n" +
                "_____________________________________");
    }

    public void decryption(String zipFilePath) throws IOException {
        byteTransform.byteArrayToFile(null, "decryption.txt");

        System.out.println("Идет процесс извлечения цифрового водяного знака...");

        List<String> imagePaths = findImageFilePaths(zipFilePath);
        Collections.sort(imagePaths);

        for (String imagePath : imagePaths) {

            BufferedImage image = readImageInZip(zipFilePath, imagePath);

            int imageHeight = image.getHeight();
            int imageWidth = image.getWidth();

            ArrayList<Character> tmpCharArrayList = new ArrayList<>();
            StringBuilder tmp = new StringBuilder();

            Color[][] rgbMatrix = rgb_yCrCb_matrix.createRGBMatrix(image, imageHeight, imageWidth);
            YCrCb[][] yCrCbMatrix = rgb_yCrCb_matrix.createYCrCbMatrix(rgbMatrix, imageHeight, imageWidth);
            double[][] yMatrix = rgb_yCrCb_matrix.createYMatrix(yCrCbMatrix, imageHeight, imageWidth);


            Matrix matY = new Matrix(imageHeight, imageWidth, yMatrix);

            ArrayList<double[][]> arrayOf8x8TransformedMatrix = waveletTransform.arrayOfMatrixDirectTransform(matY.getArrayOf8x8Matrix(yMatrix, imageHeight, imageWidth));

            for (int i = 0; i < countBits; i++) {
                double[][] tmpMatrix = arrayOf8x8TransformedMatrix.get(i);

                if (tmpMatrix[0][0] > tmpMatrix[0][1])
                    tmpCharArrayList.add('0');
                else tmpCharArrayList.add('1');
            }

            for (char ch : tmpCharArrayList)
                tmp.append(ch);

            String[] bitsStringArray = tmp.toString().split("(?<=\\G.{8})");

            byte[] byteArray = byteTransform.bitsArrayToByteArray(bitsStringArray);

            byteTransform.byteArrayToFile(byteArray, "decryption.txt");

        }
        System.out.println(
                "Цифвровой водяной знак успешно извлечен!" + "\n" +
                "_______________________________________");
    }
}
