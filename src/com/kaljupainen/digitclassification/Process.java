package com.kaljupainen.digitclassification;

import com.kaljupainen.digitclassification.service.DataGenerator;
import com.kaljupainen.digitclassification.service.DistanceCalculator;
import com.kaljupainen.digitclassification.service.DigitsUtil;
import com.kaljupainen.digitclassification.beans.Digit;

import java.util.ArrayList;
import java.util.List;

public class Process {
    
private final DataGenerator dataGenerator = new DataGenerator();
    private final DistanceCalculator distanceCalculator = new DistanceCalculator();

    public static void main(String[] args) {
        System.out.println("Process started");
        new Process().process();
    }
    
    public void process() {
        System.out.println("Processing...");

        /* Read the number of train elements from user */
        System.out.print("Enter the number of train elements: ");
        int numberOfTrainElements = Integer.parseInt(System.console().readLine());

        /* Read the number of test elements from user */
        System.out.print("Enter the number of test elements: ");
        int numberOfTestElements = Integer.parseInt(System.console().readLine());

        /* Read the noise level from user */
        System.out.print("Enter the noise level: ");
        float noiseLevel = Float.parseFloat(System.console().readLine());

        // Generate train data

        List<Digit> trainedData = new ArrayList<>();
        for (int i = 0; i < numberOfTrainElements / 3; i++) {
            trainedData.add(
                new Digit(dataGenerator.generateData("cross", noiseLevel), "cross"));
        }

        for (int i = 0; i < numberOfTrainElements / 3; i++) {
            trainedData.add(
                new Digit(dataGenerator.generateData("plus", noiseLevel), "plus"));
        }

        for (int i = 0; i < numberOfTrainElements / 3; i++) {
            trainedData.add(
                new Digit(dataGenerator.generateData("square", noiseLevel), "square"));
        }

        System.out.println("Train data generated");

        for (int k = 0; k < numberOfTestElements; k++) {
            /* Generate a test matrix */
            Digit testMatrix = new Digit(dataGenerator.generateData("cross", 1f), "cross");

            DigitsUtil.paintMatrix(testMatrix.getMatrix());

            /* Classify the test matrix */
            double smallerDistance = Integer.MAX_VALUE;
            int closerIndex = -1;
            for (int i = 0; i < trainedData.size(); i++) {
                // Find the closer element
                double currentDistance = distanceCalculator.jaccardDistance(testMatrix.getMatrix(),
                 trainedData.get(i).getMatrix());
                if (currentDistance < smallerDistance) {
                    smallerDistance = currentDistance;
                    closerIndex = i;
                }
            }

            System.out.println("More similar to: " + trainedData.get(closerIndex).getType());
        }

        System.exit(-1);
    }
}
