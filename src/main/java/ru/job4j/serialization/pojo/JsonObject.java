package ru.job4j.serialization.pojo;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonObject {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"555-777\"}");
        List<String> list = new ArrayList<>();
        list.add("stones");
        list.add("cement");
        list.add("glue");
        JSONArray jsonPurchases = new JSONArray(list);

        final Order order = new Order(new Contact("555-777"), 78544.55f, "Bob",
                true, "stones", "cement", "glue");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contact", jsonContact);
        jsonObject.put("price", order.getPrice());
        jsonObject.put("consumer", order.getConsumer());
        jsonObject.put("isPaid", order.isPaid());
        jsonObject.put("statuses", jsonPurchases);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(order).toString());
    }
}
