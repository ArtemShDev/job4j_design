package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        float height = 8.89f;
        double wayToStar = 78635244987.89;
        int age = 36;
        byte temp = 127;
        short sh = 32767;
        long countOfStars = 926146468;
        boolean isGood = true;
        char anyChar = 'a';
        LOG.debug("Variables : byte {}, short {}, int {}, long {}"
                        + ", char {}, float {}, double {}, boolean {}",
                temp, sh, age, countOfStars, anyChar, height, wayToStar, isGood);
    }
}