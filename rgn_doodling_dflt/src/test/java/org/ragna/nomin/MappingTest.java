package org.ragna.nomin;

import org.junit.Before;
import org.junit.Test;
import org.nomin.NominMapper;
import org.nomin.core.Nomin;
import org.ragna.nomin.from.*;
import org.ragna.nomin.to.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by ragnarokkrr on 26/08/14.
 */
public class MappingTest {
    NominMapper nomin = new Nomin(
            "person2employee.groovy"//, "child2kid.groovy"
    );

    Person person;
    Employee employee;

    @Before
    public void before() {
        String name = "John";
        String lastName = "Connor";
        Gender gender = Gender.MALE;
        List<Child> children;
        children = Arrays.asList(new Child("John"), new Child("Mary"));

        person = new Person(lastName, name, children, gender, new Date(), "14-11-1977");

    }


    @Test
    public void testConversion() {
        Employee e = nomin.map(person, Employee.class);


        System.out.println(e);


    }
}
