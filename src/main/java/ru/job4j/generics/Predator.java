package ru.job4j.generics;

public class Predator extends Animal {
    public Predator() {
        setNameOfClass(String.valueOf(getClass()));
    }

    @Override
    public String toString() {
        return "Predator{"
                +
                "nameOfClass='" + getNameOfClass()
                + '\''
                +
                '}';
    }
}
