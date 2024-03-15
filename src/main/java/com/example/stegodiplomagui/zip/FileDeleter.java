package com.example.stegodiplomagui.zip;

import java.io.File;

public class FileDeleter {
    public static void deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
