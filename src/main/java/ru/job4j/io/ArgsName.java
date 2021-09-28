package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public Map<String, String> getMapArgs() {
        return values;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Please, enter arguments for call function!");
        }
        Arrays.stream(args).forEach(s -> {
            int q = s.indexOf("=");
            values.put(
                s.substring((q == 0 ? 0 : 1), q), s.substring(q + 1));
        });
        if (values.containsKey("") || values.containsValue("")) {
            throw new IllegalArgumentException("Error format arguments (key=value)!");
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args)  {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}