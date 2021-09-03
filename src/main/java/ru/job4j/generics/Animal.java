package ru.job4j.generics;

public class Animal {
    private String nameOfClass;

    public Animal() {
        this.nameOfClass = String.valueOf(getClass());
    }

    public String getNameOfClass() {
        return nameOfClass;
    }

    public void setNameOfClass(String nameOfClass) {
        this.nameOfClass = nameOfClass;
    }

    @Override
    public String toString() {
        return "Animal{"
                +
                "nameOfClass='"
                + nameOfClass
                + '\''
                +
                '}';
    }
}
