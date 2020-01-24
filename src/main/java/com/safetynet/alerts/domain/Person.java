package com.safetynet.alerts.domain;

import java.util.Objects;

public class Person {
	
	private final String firstName;
	private final String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	
	public Person(String firstName, String lastName, String address, String city, String zip, 
			String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		Person person = (Person) obj;
		return (person.firstName.equals(this.firstName) && (person.lastName.equals(this.lastName)));
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}
	
//	// Builder pattern class for creating new instances of Person
//	public static class PersonBuilder {
//		
//		private String firstName;
//		private String lastName;
//		private String address;
//		private String city;
//		private String zip;
//		private String phone;
//		private String email;
//		
//		public PersonBuilder firstName(String firstName) {
//			this.firstName = firstName;
//			return this;
//		}
//		
//		public PersonBuilder lastName(String lastName) {
//			this.lastName = lastName;
//			return this;
//		}
//		
//		public PersonBuilder address(String address) {
//			this.address = address;
//			return this;
//		}
//	
//		public PersonBuilder city(String city) {
//			this.city = city;
//			return this;
//		}
//		
//		public PersonBuilder zip(String zip) {
//			this.zip = zip;
//			return this;
//		}
//		
//		public PersonBuilder phone(String phone) {
//			this.phone = phone;
//			return this;
//		}
//		
//		public PersonBuilder email(String email) {
//			this.email = email;
//			return this;
//		}
//		
//		public Person build() {
//			return new Person(firstName, lastName, address, city, zip, phone, email);
//		}
//	}
}
