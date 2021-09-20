package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("author"), is("Shmelev Artem"));
        assertThat(config.value("configversion"), is("1.0"));
        assertThat(config.value("nothing"), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFormatError() {
        String path = "./data/format_error.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("configversion"), is("1.0"));
    }
}