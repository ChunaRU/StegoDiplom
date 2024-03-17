package com.example.stegodiplomagui.stego;

import java.util.ArrayList;

public class Matrix {
    private final int rowsLength;
    private final int columnsLength;
    private double[][] matrix;

    public Matrix(int rowsLength, int columnsLength, double[][] matrix) {
        this.rowsLength = rowsLength;
        this.columnsLength = columnsLength;
        this.matrix = matrix;
    }

    public ArrayList<Double> getRow(int numOfRow) {
        ArrayList<Double> row = new ArrayList<>();
        for (int j = 0; j < columnsLength; j++)
            row.add(matrix[numOfRow][j]);
        return row;
    }

    public ArrayList<Double> getColumn(int numOfColumn) {
        ArrayList<Double> column = new ArrayList<>();
        for (int i = 0; i < rowsLength; i++)
            column.add(matrix[i][numOfColumn]);
        return column;
    }

    public void setRow(int numOfRow, ArrayList<Double> row, double[][] matrix) {
        for (int j = 0; j < columnsLength; j++)
            matrix[numOfRow][j] = row.get(j);
        setMatrix(matrix);
    }

    public void setColumn(int numOfColumn, ArrayList<Double> column, double[][] matrix) {
        for (int i = 0; i < columnsLength; i++)
            matrix[i][numOfColumn] = column.get(i);
        setMatrix(matrix);
    }

    public ArrayList<double[][]> getArrayOf8x8Matrix(double[][] mainMatrix, int imageHeight, int imageWidth) {
        ArrayList<double[][]> arrayOf8x8Matrix = new ArrayList<>();

        for (int i = 0; i < imageHeight / 8 * 8; i += 8) {
            for (int j = 0; j < imageWidth / 8 * 8; j += 8) {
                double[][] tmpMatrix = new double[8][8];
                for (int k = 0; k < 8; k++) {
                    System.arraycopy(mainMatrix[i + k], j, tmpMatrix[k], 0, 8);
                }
                arrayOf8x8Matrix.add(tmpMatrix);
            }
        }

        return arrayOf8x8Matrix;
    }

    public double[][] getBigMatrix(ArrayList<double[][]> arrayOf8x8Matrix, int imageHeight, int imageWidth) {
        double[][] bigMatrix = new double[imageHeight][imageWidth];
        int count = 0;
        for (int i = 0; i < imageHeight / 8 * 8; i += 8) {
            for (int j = 0; j < imageWidth / 8 * 8; j += 8, count++) {
                double[][] tmpMatrix = arrayOf8x8Matrix.get(count);
                for (int k = 0; k < 8; k++) {
                    System.arraycopy(tmpMatrix[k], 0, bigMatrix[i + k], j, 8);
                }
            }
        }
        return bigMatrix;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
