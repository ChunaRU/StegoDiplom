package com.example.stegodiplomagui.zip;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileNameExtractor {
    public static String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }
}
