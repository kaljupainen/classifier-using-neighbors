package com.kaljupainen.digitclassification.service;

public class DataGenerator {

   public static final int[][] baseCross = {
        {1, 0, 0, 0, 0, 0, 1},
        {0, 1, 0, 0, 0, 1, 0},
        {0, 0, 1, 0, 1, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 0, 0, 1, 0},
        {1, 0, 0, 0, 0, 0, 1}
    };

    public static final int[][] basePlus = {
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0}
    };

    public static final int[][] baseSquare = {
        {1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1}
    };

public int[][] generateData(String elementType, float difference) {
        /* This will receive the base elements and generate a matrix
        similar to the base elements but with some noise */
 
        if (difference < 0 || difference > 1) {
            throw new IllegalArgumentException("Difference must be between 0 and 1");
        }

        int baseElement[][] = null;
        switch (elementType) {
            case "cross":
                baseElement = baseCross.clone();
                break;
            case "plus":
                baseElement = basePlus.clone();
                break;
            case "square":
                baseElement = baseSquare.clone();
                break;
            default:
                return new int[5][5];
        }
        
        // Apply noise to the base element
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // Add some random noise (10% chance of flipping a bit)
                if (Math.random() < difference) {
                    baseElement[i][j] = 1 - baseElement[i][j];
                }
            }
        }

       return baseElement;
    }
}
