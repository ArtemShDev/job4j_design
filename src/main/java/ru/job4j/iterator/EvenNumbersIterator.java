package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        Optional<Integer> opt = getId();
        if (opt.isPresent()) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Optional<Integer> opt = getId();
        index = opt.get();
        return data[index++];
    }

    private Optional<Integer> getId() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

}