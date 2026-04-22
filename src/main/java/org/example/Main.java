package org.example;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Задання початкових значень (згідно з п.5 завдання)
        int rows = 4;
        int cols = 4;
        int constantA = 3;

        System.out.println("--- Лабораторна робота №1 ---");
        System.out.println("Варіант: C5=0, C7=3, C11=5\n");

        try {
            // Ініціалізація та заповнення матриці B
            int[][] matrixB = generateRandomMatrix(rows, cols, 1, 10);
            System.out.println("Початкова матриця B:");
            printMatrix(matrixB);

            // Дія 1: C = a * B
            int[][] matrixC = multiplyMatrixByConstant(matrixB, constantA);
            System.out.println("\nМатриця C (після множення на константу " + constantA + "):");
            printMatrix(matrixC);

            // Дія 2: Обчислення специфічної суми
            int targetSum = calculateSpecialSum(matrixC);
            System.out.println("\nСума (найбільші в непарних рядках + найменші в парних): " + targetSum);

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка в аргументах: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непередбачена помилка: " + e.getMessage());
        }
    }

    /**
     * Множить кожен елемент матриці на задану константу.
     */
    public static int[][] multiplyMatrixByConstant(int[][] matrix, int constant) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою або null.");
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (matrix[i] == null || matrix[i].length != cols) {
                throw new IllegalArgumentException("Матриця має бути прямокутною і не містити null-рядків.");
            }
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
        }
        return result;
    }

    /**
     * Обчислює суму найбільших елементів у непарних рядках
     * та найменших елементів у парних рядках.
     */
    public static int calculateSpecialSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою або null.");
        }

        int totalSum = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length == 0) {
                throw new IllegalArgumentException("Рядок матриці не може бути порожнім.");
            }

            int extremeValue = matrix[i][0];
            // Рядок 1 (індекс 0) - непарний. Рядок 2 (індекс 1) - парний.
            boolean isOddRowNumber = (i + 1) % 2 != 0;

            for (int j = 1; j < matrix[i].length; j++) {
                if (isOddRowNumber) {
                    if (matrix[i][j] > extremeValue) {
                        extremeValue = matrix[i][j]; // Шукаємо найбільший
                    }
                } else {
                    if (matrix[i][j] < extremeValue) {
                        extremeValue = matrix[i][j]; // Шукаємо найменший
                    }
                }
            }
            totalSum += extremeValue;
        }
        return totalSum;
    }

    // Допоміжні методи для генерації та виводу матриці
    public static int[][] generateRandomMatrix(int rows, int cols, int min, int max) {
        if (rows <= 0 || cols <= 0) throw new IllegalArgumentException("Розміри матриці мають бути > 0");
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt((max - min) + 1) + min;
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d ", val);
            }
            System.out.println();
        }
    }
}