package com.kaljupainen.digitclassification.service;

public class DistanceCalculator {
    public static double jaccardDistance(int[][] A, int[][] B) {
        /**
         * This method calculates the Jaccard distance between two binary matrices.
         * The Jaccard distance is defined as 1 - Jaccard index, where the Jaccard index
         * is the size of the intersection divided by the size of the union of the sample sets.
         */
        int intersection = 0;
        int union = 0;

        int rows = A.length;
        int cols = A[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int a = A[i][j];
                int b = B[i][j];

                // Unión: al menos uno es 1
                if (a == 1 || b == 1) {
                    union++;
                }

                // Intersección: ambos son 1
                if (a == 1 && b == 1) {
                    intersection++;
                }
            }
        }

        if (union == 0) {
            // Si ambas matrices están vacías, definimos distancia 0
            return 0.0;
        }

        double jaccardIndex = (double) intersection / union;
        return 1.0 - jaccardIndex;  // distancia Jaccard
    }

    public static void main(String[] args) {
    // Example usage
    int[][] A = {
        {1, 0, 1},
        {0, 1, 0},
        {1, 0, 1}
    };

    int[][] B = {
        {1, 1, 0},
        {0, 1, 0},
        {0, 0, 1}
    };

    double distance = jaccardDistance(A, B);
    System.out.println("Jaccard Distance: " + distance);
    }
}
