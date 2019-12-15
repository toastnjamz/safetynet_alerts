package com.safetynet.alerts.domain;

import java.util.Objects;

public class Person {
	
	private final String firstName;
	private final String lastName;
	private String address;
	private String city;
	private String zip;
	private String phoneNumber;
	private String email;
	
	private Person(String firstName, String lastName, String address, String city, String zip, 
			String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
// Builder pattern class for creating new instances of Person
	public static class Builder {
		
		private String firstName;
		private String lastName;
		private String address;
		private String city;
		private String zip;
		private String phoneNumber;
		private String email;
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder address(String address) {
			this.address = address;
			return this;
		}
	
		public Builder city(String city) {
			this.city = city;
			return this;
		}
		
		public Builder zip(String zip) {
			this.zip = zip;
			return this;
		}
		
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Person build() {
			return new Person(firstName, lastName, address, city, zip, phoneNumber, email);
		}
	}
}
