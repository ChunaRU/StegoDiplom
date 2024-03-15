package com.example.stegodiplomagui.zip;

public class FileFormatExtractor {
    public static String getFileFormat(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1);
        } else {
            return "Unknown";
        }
    }
}
