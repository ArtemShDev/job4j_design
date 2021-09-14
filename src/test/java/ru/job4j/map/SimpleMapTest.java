package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putTwoEntries() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Price", 100);
        simpleMap.put("Discount", 10);
        Iterator<String> iterator = simpleMap.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Discount", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Price", iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void putTwoEntriesGetThreeEntries() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Price", 100);
        simpleMap.put("Discount", 10);
        Iterator<String> iterator = simpleMap.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test
    public void getDiscount() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Price", 100);
        simpleMap.put("DiscountVip_OnlyForYou", 10);
        simpleMap.put("BestPrice", 55);
        Assert.assertEquals(Optional.of(10), Optional.ofNullable(simpleMap.get("DiscountVip_OnlyForYou")));
    }

    @Test
    public void getNull() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Price", 100);
        simpleMap.put("BestPrice", 55);
        Assert.assertEquals(Optional.empty(), Optional.ofNullable(simpleMap.get("DiscountVip_OnlyForYou")));
    }

    @Test
    public void remove() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Price", 100);
        simpleMap.put("BestPrice", 55);
        assertTrue(simpleMap.remove("BestPrice"));
        assertFalse(simpleMap.remove("BestPrice"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void iterator() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put("Price", 100);
        Iterator<String> iterator = simpleMap.iterator();
        simpleMap.put("Discount", 10);
        assertEquals("Price", iterator.next());
    }
}