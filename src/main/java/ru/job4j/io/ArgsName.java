package ru.job4j.io;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please, enter arguments for call function!");
        }
        Arrays.stream(args).forEach(s -> values.put(
                s.substring(1, s.indexOf("=")), s.substring(s.indexOf("=") + 1)));
        if (values.containsValue("")) {
            throw new IllegalArgumentException("Error format arguments (key=value)!");
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        try {
            names.parse(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    public static void main(String[] args)  {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}