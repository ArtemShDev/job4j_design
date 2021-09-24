package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String str = in.readLine();
            String startTime = "";
            while (str != null) {
                String[] arr = str.split(" ");
                if (arr[0].equals("400") || arr[0].equals("500")) {
                    if (startTime.equals("")) {
                        startTime = arr[1];
                        out.printf("%s%s", arr[1], ";");
                    }
                } else if (!startTime.equals("")) {
                    startTime = "";
                    out.printf("%s%s%n", arr[1], ";");
                }
                str = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("server_log.txt", "target.txt");
    }
}