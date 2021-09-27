package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Please, enter 3 arguments for call function "
                    + "(directory, exc. expansion, output file)!");
        }
        ArgsName argsNames = ArgsName.of(args);
        Path start = Paths.get(argsNames.get("d"));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Path %s is not exist!",
                    start.toAbsolutePath()));
        }
        List<File> files = Search.search(start, p -> !p.toFile().getName()
                .endsWith(argsNames.get("e")))
                .stream().map(Path::toFile).collect(Collectors.toList());
        packFiles(files, Path.of(argsNames.get("o")).toFile());
    }
}