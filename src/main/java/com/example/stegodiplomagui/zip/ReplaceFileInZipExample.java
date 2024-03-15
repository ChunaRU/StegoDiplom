package com.example.stegodiplomagui.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ReplaceFileInZipExample {
    public static void replaceFileInZipExample(String sourceFilePath, String zipFilePath, String targetFolder){
        try {
            File sourceFile = new File(sourceFilePath);
            File zipFile = new File(zipFilePath);

            File tempFile = File.createTempFile("temp", null);

            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zis = new ZipInputStream(fis);

            FileOutputStream fos = new FileOutputStream(tempFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            ZipEntry entry = zis.getNextEntry();
            byte[] buffer = new byte[1024];

            while (entry != null) {
                String name = entry.getName();

                if (name.equals(targetFolder)) {
                    entry = zis.getNextEntry();
                    continue;
                }

                zos.putNextEntry(new ZipEntry(name));

                int length;
                while ((length = zis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                entry = zis.getNextEntry();
            }

            zos.putNextEntry(new ZipEntry(targetFolder));

            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            fileInputStream.close();

            zis.closeEntry();
            zis.close();
            zos.close();

            zipFile.delete();
            tempFile.renameTo(zipFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
