package com.example.stegodiplomagui.stego.wavelet;

import com.example.stegodiplomagui.stego.Matrix;

import java.util.ArrayList;

public class WaveletTransform {
    public ArrayList<Double> directTransform(ArrayList<Double> SourceList) {
        if (SourceList.toArray().length == 1)
            return SourceList;

        ArrayList<Double> RetVal = new ArrayList<>();
        ArrayList<Double> TmpArr = new ArrayList<>();

        for (int j = 0; j < SourceList.toArray().length - 1; j += 2) {
            TmpArr.add((SourceList.get(j) + SourceList.get(j + 1)) / 2.0);
            RetVal.add((SourceList.get(j) - SourceList.get(j + 1)) / 2.0);
        }

        RetVal.addAll(TmpArr);
        return RetVal;
    }

    public ArrayList<Double> inverseTransform(ArrayList<Double> SourceList) {
        if (SourceList.toArray().length == 1)
            return SourceList;

        ArrayList<Double> RetVal = new ArrayList<>();
        ArrayList<Double> TmpPart = new ArrayList<>();

        for (int i = SourceList.toArray().length / 2; i < SourceList.toArray().length; i++)
            TmpPart.add(SourceList.get(i));


        for (int i = 0; i < SourceList.toArray().length / 2; i++) {
            RetVal.add(TmpPart.get(i) + SourceList.get(i));
            RetVal.add(TmpPart.get(i) - SourceList.get(i));
        }

        return RetVal;
    }

    public double[][] matrixDirectTransform(double[][] matrix) {
        Matrix matrixObject = new Matrix(matrix.length, matrix.length, matrix);

        for (int i = 0; i < matrix.length; i++) {
            ArrayList<Double> tmpArray = matrixObject.getRow(i);
            matrixObject.setRow(i, directTransform(tmpArray), matrix);
        }

        for (int i = 0; i < matrix.length; i++) {
            ArrayList<Double> tmpArray = matrixObject.getColumn(i);
            matrixObject.setColumn(i, directTransform(tmpArray), matrix);
        }
        return matrixObject.getMatrix();
    }

    public double[][] matrixInverseTransform(double[][] matrix) {
        Matrix matrixObject = new Matrix(matrix.length, matrix.length, matrix);

        for (int i = 0; i < matrix.length; i++) {
            ArrayList<Double> tmpArray = matrixObject.getColumn(i);
            matrixObject.setColumn(i, inverseTransform(tmpArray), matrix);
        }

        for (int i = 0; i < matrix.length; i++) {
            ArrayList<Double> tmpArray = matrixObject.getRow(i);
            matrixObject.setRow(i, inverseTransform(tmpArray), matrix);
        }
        return matrixObject.getMatrix();
    }

    public ArrayList<double[][]> arrayOfMatrixDirectTransform(ArrayList<double[][]> arrayOf8x8Matrix) {
        ArrayList<double[][]> arrayOfMatrixDirectTransform = new ArrayList<>();

        for (double[][] of8x8Matrix : arrayOf8x8Matrix) {
            double[][] tmpMatrix = matrixDirectTransform(new Matrix(8, 8, of8x8Matrix).getMatrix());
            arrayOfMatrixDirectTransform.add(tmpMatrix);
        }

        return arrayOfMatrixDirectTransform;
    }

    public ArrayList<double[][]> arrayOfMatrixInverseTransform(ArrayList<double[][]> arrayOf8x8Matrix) {
        ArrayList<double[][]> arrayOfMatrixInverseTransform = new ArrayList<>();

        for (double[][] of8x8Matrix : arrayOf8x8Matrix) {
            double[][] tmpMatrix = matrixInverseTransform(new Matrix(8, 8, of8x8Matrix).getMatrix());
            arrayOfMatrixInverseTransform.add(tmpMatrix);
        }

        return arrayOfMatrixInverseTransform;
    }
}
