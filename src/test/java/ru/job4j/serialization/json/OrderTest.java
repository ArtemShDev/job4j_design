package ru.job4j.serialization.json;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void fromJsonToOrder() {
        Order order = new Order(new Contact("555-777"), 78544.55f, "Bob",
                true, "stones", "cement", "glue");
        String orderJson = "{"
                + "\"contact\":{\"phone\":\"555-777\"},"
                + "\"price\":78544.55,\"consumer\":\"Bob\",\"isPaid\":true,"
                + "\"purchases\":[\"stones\",\"cement\",\"glue\"]}";
        assertThat(order.fromJsonToOrder(orderJson), is(order));
    }

    @Test
    public void fromOrderToJson() {
        Order order = new Order(new Contact("555-777"), 78544.55f, "Bob",
                true, "stones", "cement", "glue");
        String orderJson = "{"
                + "\"contact\":{\"phone\":\"555-777\"},"
                + "\"price\":78544.55,\"consumer\":\"Bob\",\"isPaid\":true,"
                + "\"purchases\":[\"stones\",\"cement\",\"glue\"]}";
        assertThat(order.fromOrderToJson(order), is(orderJson));
    }
}