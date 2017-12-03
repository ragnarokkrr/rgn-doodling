package org.ragna.doodling.xml;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class XmlJaxb {
	Person person;
	boolean expected;

	static enum PhoneType {
		CEL, HOME, WORK
	}

	static class Phone {
		int num;
		PhoneType phoneType;

		public Phone(int num, PhoneType phoneType) {
			super();
			this.num = num;
			this.phoneType = phoneType;
		}

		public int getNum() {
			return num;
		}

		public PhoneType getPhoneType() {
			return phoneType;
		}

		@Override
		public String toString() {
			return "Phone [num=" + num + ", phoneType=" + phoneType + "]";
		}

	}

	static class Person {
		String name;
		List<Phone> phones;

		public Person(String name, Object[][] phones) {
			super();
			this.name = name;
			this.phones = new ArrayList<>();
			for (Object[] phone : phones) {
				this.phones
						.add(new Phone((int) phone[0], (PhoneType) phone[1]));
			}
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Phone> getPhones() {
			return phones;
		}

		public void setPhones(List<Phone> phones) {
			this.phones = phones;
		}

		@Override
		public String toString() {
			return "Person [name=" + name + ", phones=" + phones + "]";
		}

	}

	public XmlJaxb(String name, Object[][] phone, boolean expected) {
		this.person = new Person(name, phone);
		this.expected = expected;
	}

	@Parameters(name = "{index}-> Name:{0}, Phone:{1}, Expected:{2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(
				set("Maria", new Object[][] { { 10, PhoneType.WORK },
						{ 20, PhoneType.CEL } }, Boolean.TRUE),
				set("Joao", new Object[][] { { 30, PhoneType.HOME },
						{ 40, PhoneType.CEL } }, Boolean.TRUE),
				set("Ze", new Object[][] { { 50, PhoneType.HOME },
						{ 60, PhoneType.CEL } }, Boolean.FALSE)); //
	}

	private static Object[] set(Object... values) {
		return values;
	}

	@Test
	public void test() {
		assertTrue(expected);
	}

}
