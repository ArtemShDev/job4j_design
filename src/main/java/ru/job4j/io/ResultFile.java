package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        };
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    out.write(Integer.toString((i + 1) * (j + 1)).getBytes());
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
