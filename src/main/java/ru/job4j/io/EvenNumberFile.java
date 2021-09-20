package ru.job4j.io;

import java.io.FileInputStream;
import java.util.stream.Stream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            Stream.of(text.toString()).map(st -> st.split(System.lineSeparator()))
                    .flatMap(Stream::of)
                    .mapToInt(Integer::valueOf)
                    .forEach(c -> System.out.println("Число " + c + (c % 2 == 0 ? " четное" : " нечетное")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
