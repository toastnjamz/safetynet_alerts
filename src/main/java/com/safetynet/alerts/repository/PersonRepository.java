package com.safetynet.alerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.jsonloader.JsonLoader;
import com.safetynet.alerts.domain.Person;

@Repository
public class PersonRepository {
	
	private List<Person> personList;
	
	@Autowired
	public PersonRepository(JsonLoader jsonLoader) {
		this.personList = jsonLoader.getPersons();
	}
	
	public List<Person> findAll() {
		return personList;
	}

	public Person findPerson(String firstName, String lastName) {
		for (Person person : personList) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
	}
	
	public Person createPerson(Person person) {
		personList.add(person);
		return person;
	}
	
	public void updatePerson(Person person) {
		Person updatePerson = findPerson(person.getFirstName(), person.getLastName());
		updatePerson.setAddress(person.getAddress());
		updatePerson.setCity(person.getCity());
		updatePerson.setZip(person.getZip());
		updatePerson.setPhone(person.getPhone());
		updatePerson.setEmail(person.getEmail());
	}
	
	public void deletePerson(String firstName, String lastName) {
		Person deletePerson = findPerson(firstName, lastName);
		personList.remove(deletePerson);
	}
}