package ragna.springbootrx.model;

import java.util.Date;


public class Invoice {
    private final String name;
    private final Date date;

    public Invoice(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
