package com.kaljupainen.digitclassification.beans;

public class Digit {
    private int[][] matrix;

    private String type;
    
    public Digit(int[][] matrix, String type) {
        this.matrix = matrix;
        this.type = type;
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
}
