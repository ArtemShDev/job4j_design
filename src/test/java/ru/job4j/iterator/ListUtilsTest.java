package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfterIndexOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(0, 1, 3, 2), Is.is(input));
    }

    @Test
        public void whenRemoveOne() {
            List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
            ListUtils.removeIf(input, var -> var == 1);
            assertThat(Arrays.asList(0, 2), Is.is(input));
    }

    @Test
    public void whenRemoveExcOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.removeIf(input, var -> var != 1);
        assertThat(Arrays.asList(1), Is.is(input));
    }

    @Test
    public void whenReplaceExcOne() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 7));
        ListUtils.replaceIf(input, var -> var != 1, 5);
        assertThat(Arrays.asList(5, 1, 5, 5), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 5, 12, 17, 22));
        ListUtils.removeAll(input, Arrays.asList(1, 12, 17));
        assertThat(Arrays.asList(0, 2, 5, 22), Is.is(input));
    }

}