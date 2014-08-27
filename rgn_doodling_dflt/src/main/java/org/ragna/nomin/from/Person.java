package org.ragna.nomin.from;

import java.util.Date;
import java.util.List;

/**
 * Created by ragnarokkrr on 26/08/14.
 */
public class Person {

    private String lastName;
    private String name;
    private List<Child> children;
    private Gender gender;
    private Date birthDate;
    private String strDate;

    public Person() {

    }

    public Person(String lastName, String name, List<Child> children, Gender gender, Date birthDate, String strDate) {
        this.lastName = lastName;
        this.name = name;
        this.children = children;
        this.gender = gender;
        this.birthDate = birthDate;
        this.strDate = strDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", children=" + children +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", strDate='" + strDate + '\'' +
                '}';
    }
}
