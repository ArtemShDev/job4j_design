package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        validation(argsName);
        String out = argsName.get("out");
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            List<String> filter = List.of(argsName.get("filter").split(","));
            String delimiter = argsName.get("delimiter");
            List<Integer> listFields = new ArrayList<>();
            while (scanner.hasNext()) {
                String resultFields = scanner.nextLine();
                String[] fields = resultFields.split(delimiter);
                if (listFields.size() == 0) {
                    for (int i = 0; i < fields.length; i++) {
                        if (filter.contains(fields[i])) {
                            listFields.add(i);
                        }
                    }
                }
                for (int i : listFields) {
                    String appendChar = (listFields.indexOf(i) == listFields.size() - 1 ? "" : ";");
                    sb.append(fields[i]).append(appendChar);
                }
                sb.append(System.lineSeparator());
            }
        }
        if (!out.equals("stdout")) {
            try (BufferedOutputStream outFile = new BufferedOutputStream(
                    new FileOutputStream(out))) {
                outFile.write(sb.toString().getBytes());
            }
        } else {
            System.out.println(sb);
        }
    }

    public static void validation(ArgsName argsName) throws Exception {
        Map<String, String> map = argsName.getMapArgs();
        if (!(map.containsKey("path") && map.containsKey("filter")
                && map.containsKey("delimiter") && map.containsKey("out"))) {
            throw new IllegalArgumentException("Please, enter 4 arguments for call function "
                    + "(directory, delimiter, out, filter)!");
        }
        Path scan = Paths.get(argsName.get("path"));
        if (!scan.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Path %s is not exist!",
                    scan.toAbsolutePath()));
        }
    }
}