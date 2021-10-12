package ru.job4j.io.exam;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Finder {

    public static void main(String[] args) {
        try {
            Map<String, String> mapArgs = mapBuilder(args);
            validate(mapArgs);
            List<Path> searchFiles = Search.search(Paths.get(mapArgs.get("d")),
                    predicateBuilder(mapArgs.get("n"), mapArgs.get("t")));
            writeResult(mapArgs.get("o"), searchFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> predicateBuilder(String filter, String mask) {
        Predicate<Path> pr = p -> p.toFile().getName().equals(filter);
        if (mask.equals("regex") || mask.equals("mask")) {
            String modifFilter = filter.replace("?", ".").replace("*", ".+?");
            pr = p -> Pattern.matches(modifFilter, p.toFile().getName());
        }
        return pr;
    }

    private static void writeResult(String logFile, List<Path> searchFiles) throws Exception {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(logFile))) {
            for (Path path : searchFiles) {
                out.printf("%s%n", path);
            }
        }
    }

    private static Map<String, String> mapBuilder(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        return argsName.getMapArgs();
    }

    private static void validate(Map<String, String> mapArgs) throws Exception {
        if (!(mapArgs.containsKey("d") && mapArgs.containsKey("n")
                && mapArgs.containsKey("t") && mapArgs.containsKey("o"))) {
            throw new IllegalArgumentException("Please, enter 4 arguments for call function "
                    + "Example: ... -d=c:/ -n=*.txt -t=mask -o=log.txt (directory, filter, mask, log file)!");
        }
        Path scan = Paths.get(mapArgs.get("d"));
        if (!scan.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Path %s is not exist!",
                    scan.toAbsolutePath()));
        }
        String mask = mapArgs.get("t");
        if (!(mask.equals("mask") || mask.equals("name") || mask.equals("regex"))) {
            throw new IllegalArgumentException(String.format("Mask should be %s or %s or %s !",
                    "\"mask\"", "\"name\"", "\"regex\""));
        }
    }
}
