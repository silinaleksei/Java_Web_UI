package org.example.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {

    @Test
    @DisplayName("Test with the correct data")
    public void testsquareOfTriangleCorrectSides() throws MyException {
        assertEquals(43.0, Triangle.squareOfTriangle(10, 10, 10));
    }

    @ParameterizedTest
    @DisplayName("Test with correct data from csv source")
    @CsvSource({"3, 4, 5, 6.0", "7, 8, 9, 27.0", "10, 10, 10, 43.0", "100, 100, 100, 4330.0"})
    public void testsquareOfTriangleCorrectSidesCsv(int a, int b, int c, double result) throws MyException {
        assertEquals(result, Triangle.squareOfTriangle(a, b, c));
    }

    @Test
    @DisplayName("Test with incorrect data: one side=0")
    public void testsquareOfTriangleZero() {
        Assertions.assertThrows(MyException.class, () -> Triangle.squareOfTriangle(10, 10, 0));
    }

    @ParameterizedTest
    @DisplayName("Test with incorrect data from csv source: one side=0")
    @CsvSource({"0, 4, 5", "7, 0, 9", "10, 10, 0"})
    public void testsquareOfTriangleZeroCsv(int a, int b, int c) {
        Assertions.assertThrows(MyException.class, () -> Triangle.squareOfTriangle(a, b, c));
    }

    @Test
    @DisplayName("Test with incorrect data: one side - negative number")
    public void testsquareOfTriangleNegativeNumber() {
        Assertions.assertThrows(MyException.class, () -> Triangle.squareOfTriangle(10, 10, -1));
    }

    @ParameterizedTest
    @DisplayName("Test with incorrect data from csv source: one side - negative number")
    @CsvSource({"-3, 4, 5", "7, -8, 9", "10, 10, -10"})
    public void testsquareOfTriangleNegativeNumberCsv(int a, int b, int c) {
        Assertions.assertThrows(MyException.class, () -> Triangle.squareOfTriangle(a, b, c));
    }

    @Test
    @DisplayName("Test with incorrect data: sum two sides < third")
    public void testsquareOfTriangleWrongSides() {
        Assertions.assertThrows(MyException.class, () -> Triangle.squareOfTriangle(1, 2, 10));
    }

    @ParameterizedTest
    @DisplayName("Test with incorrect data from csv source: sum two sides < third")
    @CsvSource({"1, 2, 10", "1, 10, 2", "10, 1, 2"})
    public void testsquareOfTriangleWrongSidesCsv(int a, int b, int c) {
        Assertions.assertThrows(MyException.class, () -> Triangle.squareOfTriangle(a, b, c));
    }


    @ParameterizedTest
    @DisplayName("Test with correct data from csv file")
    @CsvFileSource(resources = "/test-data.csv", delimiter = ',', numLinesToSkip = 1)
    public void testsquareOfTriangleCorrectSidesCsvFile(int a, int b, int c, double result) throws MyException {
        assertEquals(result, Triangle.squareOfTriangle(a, b, c));
    }
}
