package com.example.stegodiplomagui.zip;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipImageReader {
    public static BufferedImage readImageInZip(String zipFilePath, String imageFilePathInZip) throws IOException {
        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            ZipEntry entry = zipFile.getEntry(imageFilePathInZip);
            if (entry != null) {
                BufferedInputStream inputStream = new BufferedInputStream(zipFile.getInputStream(entry));
                BufferedImage image = ImageIO.read(inputStream);
                inputStream.close();
                return image;
            } else {
                throw new IOException("Image not found in the zip archive: " + imageFilePathInZip);
            }
        }
    }
}
