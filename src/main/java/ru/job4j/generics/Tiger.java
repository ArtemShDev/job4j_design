package ru.job4j.generics;

public class Tiger extends Predator {
    public Tiger() {
        setNameOfClass(String.valueOf(getClass()));
    }

    @Override
    public String toString() {
        return "Tiger{"
                +
                "nameOfClass='" + getNameOfClass()
                + '\''
                +
                '}';
    }
}
