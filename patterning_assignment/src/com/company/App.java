package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

        App main = new App();
        int matrix2D[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        main.specialPrint(matrix2D);
    }

    public void specialPrint(int matrix2D[][]) {
        List<Integer> result = new ArrayList<>();
        // don't waste cpu cycles if the input is broken
        if (matrix2D == null || matrix2D.length == 0) return;

        int currentRow = 0;
        int currentCol = 0;
        int rowLength = matrix2D.length - 1;
        int colLength = matrix2D[0].length - 1;

        // traversing until reaching the boundary of the matrix
        while (currentRow <= rowLength && currentCol <= colLength) {
            //direction: right
            for (int i = currentCol; i <= colLength; i++)
                result.add(matrix2D[currentRow][i]);

            //direction: down
            for (int i = currentRow + 1; i <= rowLength; i++)
                result.add(matrix2D[i][colLength]);

            //direction: left
            for (int i = colLength - 1; i >= currentCol; i--) {
                // in case this cell is visited before
                if (currentRow == rowLength)
                    break;
                result.add(matrix2D[rowLength][i]);
            }

            //direction: up
            for (int i = rowLength - 1; i >= currentRow + 1; i--) {
                // in case this cell is visited before
                if (currentCol == colLength)
                    break;
                result.add(matrix2D[i][currentRow]);
            }

            currentCol++;
            colLength--;
            currentRow++;
            rowLength--;
        }

        System.out.println(result.stream().map(it -> it.toString())
                .collect(Collectors.joining(",")));
    }

}