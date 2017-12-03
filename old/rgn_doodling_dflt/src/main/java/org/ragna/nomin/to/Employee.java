package org.ragna.nomin.to;

/**
 * Created by ragnarokkrr on 26/08/14.
 */
public class Employee {

    private Details details;
    private String last;
    private String name;

    public Employee() {
    }

    public Employee(Details details, String last, String name) {
        this.details = details;
        this.last = last;
        this.name = name;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "details=" + details +
                ", last='" + last + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
