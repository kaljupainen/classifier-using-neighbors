package com.kaljupainen.digitclassification.service;

public class DigitsUtil {
    public static void paintMatrix(int[][] matrix) {
        System.out.println();
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value == 1 ? "O" : " ");
            }
            System.out.println();
        }
    }  
}
