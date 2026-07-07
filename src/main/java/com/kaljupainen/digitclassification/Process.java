package com.kaljupainen.digitclassification;

import com.kaljupainen.digitclassification.service.DataGenerator;
import com.kaljupainen.digitclassification.service.DistanceCalculator;
import com.kaljupainen.digitclassification.service.DigitsUtil;
import com.kaljupainen.digitclassification.beans.Digit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Process {
    
private final DataGenerator dataGenerator = new DataGenerator();

    public static void main(String[] args) {
        System.out.println("Process started");
        new Process().process();
    }
    
    public void process() {
        System.out.println("Processing...");
        Scanner scanner = new Scanner(System.in);

        /* Read the number of train elements from user */
        System.out.print("Enter the number of train elements: ");
        int numberOfTrainElements = Integer.parseInt(scanner.nextLine());

        /* Read the number of test elements from user */
        System.out.print("Enter the number of test elements: ");
        int numberOfTestElements = Integer.parseInt(scanner.nextLine());

        /* Read the noise level from user */
        System.out.print("Enter the noise level: ");
        float noiseLevel = Float.parseFloat(scanner.nextLine());

        System.out.println("---------------------------------------------------");
        System.out.println("Number of train element: " + numberOfTrainElements);
        System.out.println("Number of test element: " + numberOfTestElements);
        System.out.println("Noise level: " + noiseLevel);
        System.out.println("---------------------------------------------------");
        
        // Generate train data

        System.out.println("[1] Generating training data");
        
        List<Digit> trainedData = new ArrayList<>();
        for (int i = 0; i < (numberOfTrainElements / 3) - 1; i++) {
            trainedData.add(
                new Digit(dataGenerator.generateData("cross", noiseLevel, true), "cross"));
        }

        trainedData.add(
                new Digit(dataGenerator.generateData("cross", noiseLevel, false), "cross"));

        for (int i = 0; i < (numberOfTrainElements / 3) - 1; i++) {
            trainedData.add(
                new Digit(dataGenerator.generateData("plus", noiseLevel, true), "plus"));
        }

        trainedData.add(
                new Digit(dataGenerator.generateData("plus", noiseLevel, false), "plus"));

        for (int i = 0; i < (numberOfTrainElements / 3) - 1; i++) {
            trainedData.add(
                new Digit(dataGenerator.generateData("square", noiseLevel, true), "square"));
        }

        trainedData.add(
                new Digit(dataGenerator.generateData("square", noiseLevel, false), "square"));

        System.out.println("[2] Train data generated, starting testing");

        Random random = new Random();

        for (int k = 0; k < numberOfTestElements; k++) {
        	
            int numero = random.nextInt(3);
          
            Digit testMatrix = null;
            switch(numero) {
            case 0:
                /* Generate a test matrix */
                testMatrix = new Digit(dataGenerator.generateData("cross", noiseLevel, true), "cross");
                break;
            case 1:
                testMatrix = new Digit(dataGenerator.generateData("plus", noiseLevel, true), "plus");
            		break;
            case 2:
                testMatrix = new Digit(dataGenerator.generateData("square", noiseLevel, true), "square");
            		break;
            }
 
            
            System.out.println("[" + (k + 3) + ".1] Testing figure:\n");
            DigitsUtil.paintMatrix(testMatrix.getMatrix());
            System.out.println("\n");

            /* Classify the test matrix */
            double smallerDistance = Integer.MAX_VALUE;
            int closerIndex = -1;
            for (int i = 0; i < trainedData.size(); i++) {
                // Find the closer element
                double currentDistance = DistanceCalculator.jaccardDistance(testMatrix.getMatrix(),
                 trainedData.get(i).getMatrix());
                if (currentDistance < smallerDistance) {
                    smallerDistance = currentDistance;
                    closerIndex = i;
                }
            }

            if (closerIndex >= 0) {
            		System.out.println("[" + (k + 3) + ".2]More similar to: " + trainedData.get(closerIndex).getType() + "\n");
            } else {
            		System.out.println("[" + (k + 3) + ".2]FAIL TO CLASSIFY\n");
            }
            
        }

        System.exit(-1);
    }
}
