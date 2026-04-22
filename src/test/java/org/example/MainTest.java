package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMultiplyMatrixByConstant() {
        // Тестуємо множення на константу (C5=0)
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        int constant = 3;
        int[][] expected = {
                {3, 6},
                {9, 12}
        };

        int[][] result = Main.multiplyMatrixByConstant(input, constant);
        assertArrayEquals(expected, result, "Матриця має бути помножена на константу 3");
    }

    @Test
    void testCalculateSpecialSum() {
        // Тестуємо логіку C11=5:
        // Рядок 1 (непарний, індекс 0) -> шукаємо МАКСИМУМ
        // Рядок 2 (парний, індекс 1)   -> шукаємо МІНІМУМ
        int[][] matrix = {
                {10, 2, 55}, // Max = 55
                {10, 2, 55}, // Min = 2
                {1, 8, 3}    // Max = 8
        };

        // Очікувана сума: 55 + 2 + 8 = 65
        int expectedSum = 65;
        int actualSum = Main.calculateSpecialSum(matrix);

        assertEquals(expectedSum, actualSum, "Сума (max непарних + min парних) розрахована невірно");
    }

    @Test
    void testEmptyMatrixHandling() {
        // Перевіряємо, чи програма правильно реагує на некоректні дані (обробка виключень)
        int[][] emptyMatrix = new int[0][0];

        assertThrows(IllegalArgumentException.class, () -> {
            Main.calculateSpecialSum(emptyMatrix);
        }, "Програма має видавати помилку для порожньої матриці");
    }
}