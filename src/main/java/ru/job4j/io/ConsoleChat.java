package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(in);
        String word;
        String command = "";
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();
        while (!(word = scanner.next()).equals(OUT)) {
            log.add(word);
            command = word.equals(STOP) || word.equals(CONTINUE) ? word : command;
            if (!command.equals(STOP)) {
                String phrase = phrases.get((int) (Math.random() * phrases.size()));
                log.add(phrase);
                System.out.println(phrase);
            }
        }
        log.add(word);
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("loggg.txt", "botAnswers.txt");
        cc.run();
    }
}