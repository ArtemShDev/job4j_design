package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, String> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            FileProperty fp = new FileProperty(Files.size(file), file.getFileName().toString());
            map.merge(fp, file.toAbsolutePath().toString(),
                    (oldValue, newValue) -> String.format("%s;%n%s;", oldValue, newValue));
        }
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, String> getDuplicates() {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().contains(";"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}