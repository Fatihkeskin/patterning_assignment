package com.company;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestClass {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    private static App app;

    @BeforeAll
    static void setup() {
        app = new App();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    static void done() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @DisplayName("Case 1")
    @Test
    void checkGivenTestCase() {
        int[][] matrix2D = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        app.specialPrint(matrix2D);
        assertEquals(outContent.toString(),
                "1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10\r\n");
    }

    @DisplayName("print only one element array")
    @Test
    void print1Element() {
        int[][] matrix2D = {{1}};
        app.specialPrint(matrix2D);
        assertEquals(outContent.toString(), "1\r\n");
    }

    @DisplayName("print the elements of a null array")
    @Test
    void printNullMatrix() {
        int[][] matrix2D = null;
        app.specialPrint(matrix2D);
        assertEquals(outContent.toString(), "");
    }


    @DisplayName("print the elements of a different column sized array")
    @Test
    void printNonEqualMatrix() {
        int[][] matrix2D = {{1, 2, 4}, {5, 6, 7, 8}, {9, 10, 11, 12, 25}, {13, 14, 15, 16}};
        app.specialPrint(matrix2D);
        assertEquals(outContent.toString(), "1,2,4,7,11,15,14,13,9,5,6,10\r\n");
    }
}
