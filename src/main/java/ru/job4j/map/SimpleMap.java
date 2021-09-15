package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (key == null) {
            return false;
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && hash(table[index].key.hashCode()) == hash(key.hashCode())
                && table[index].key.equals(key)) {
            return false;
        }
        if (count >= (int) (capacity * LOAD_FACTOR)) {
            expand();
        }
        index = indexFor(hash(key.hashCode()));
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] tableSrc = Arrays.copyOf(table, capacity);
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> e : tableSrc) {
            if (e != null) {
                table[indexFor(hash(e.key.hashCode()))] = e;
            }
        }
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = table[indexFor(hash(key.hashCode()))];
        if (entry != null && entry.key.equals(key)) {
            return entry.value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        MapEntry<K, V> entry = table[indexFor(hash(key.hashCode()))];
        if (entry != null && entry.key.equals(key)) {
            table[indexFor(hash(key.hashCode()))] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            private int countIt;
            @Override
            public boolean hasNext() {
                while (countIt < capacity) {
                    if (table[countIt] != null) {
                        return true;
                    }
                    countIt++;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return table[countIt++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}