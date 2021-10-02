package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void incSize() {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public void add(T value) {
        incSize();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T oldValue = container[index];
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T remValue = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - 1 - index);
        size--;
        container[size] = null;
        modCount++;
        return remValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            private int count;
            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
                     public T next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        if (expectedModCount != modCount) {
                            throw new ConcurrentModificationException();
                        }
                return container[count++];
            }

        };
    }
}