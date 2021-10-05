package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"consumer", "contact", "purchases"})
@XmlSeeAlso(value = {Contact.class})
public class Order {

    private Contact contact;
    @XmlAttribute
    private float price;
    private String consumer;
    @XmlAttribute
    private boolean isPaid;
    @XmlElementWrapper(name = "purchases")
    @XmlElement(name = "purchase")
    private String[] purchases;

    public Order() {
    }

    public Order(Contact contact, float price, String consumer, boolean isPaid, String... purchases) {
        this.contact = contact;
        this.price = price;
        this.consumer = consumer;
        this.isPaid = isPaid;
        this.purchases = purchases;
    }

    public static void main(String[] args) throws Exception {
        Order order = new Order(new Contact("555-777"), 78544.55f, "Bob",
                true, "stones", "cement", "glue");
        JAXBContext context = JAXBContext.newInstance(Order.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(order, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Order result = (Order) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;
        return Float.compare(order.price, price) == 0 && isPaid == order.isPaid
                && Objects.equals(contact, order.contact)
                && Objects.equals(consumer, order.consumer)
                && Arrays.equals(purchases, order.purchases);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(contact, price, consumer, isPaid);
        result = 31 * result + Arrays.hashCode(purchases);
        return result;
    }
}

