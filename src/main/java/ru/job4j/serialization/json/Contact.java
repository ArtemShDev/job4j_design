package ru.job4j.serialization.json;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private final String phone;

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
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
        Contact contact = (Contact) o;
        return Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}