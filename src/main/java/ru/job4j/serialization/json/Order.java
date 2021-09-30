package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Order {
    private final Contact contact;
    private final float price;
    private final String consumer;
    private final boolean isPaid;
    private final String[] purchases;

    public Order(Contact contact, float price, String consumer, boolean isPaid, String... purchases) {
        this.contact = contact;
        this.price = price;
        this.consumer = consumer;
        this.isPaid = isPaid;
        this.purchases = purchases;
    }

    public static void main(String[] args) {
        Order order = new Order(new Contact("555-777"), 78544.55f, "Bob",
                true, "stones", "cement", "glue");
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(order));

        final String orderJson = "{"
        + "\"contact\":{\"phone\":\"555-777\"},"
        + "\"price\":78544.55,\"consumer\":\"Bob\",\"isPaid\":true,"
        + "\"purchases\":[\"stones\",\"cement\",\"glue\"]}";
        final Order orderMod = gson.fromJson(orderJson, Order.class);
        System.out.println(orderMod);
    }

    @Override
    public String toString() {
        return "Order{"
                + "contact=" + contact
                + ", price=" + price
                + ", consumer='" + consumer
                + '\''
                + ", isPaid=" + isPaid
                + ", purchases=" + Arrays.toString(purchases)
                + '}';
    }
}
