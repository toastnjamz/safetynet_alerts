package com.safetynet.alerts.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.domain.Person;

@Repository
public class PersonRepository {

	private List<Person> personList = new ArrayList<Person>();
	
	public List<Person> findAll() {
		return personList;
	}

	public void createPerson(Person person) {
		personList.add(person);
	}
	
	// Do I need this?
	public Person findPerson(String firstAndLastName) {
		for (Person person : personList) {
			if (person.getLastName().equals(firstAndLastName)) {
				return person;
			}
		}
		return null;
	}

	public void updatePerson(String firstAndLastName) {
		
	}

	public void deletePerson(String firstAndLastName) {
		
	}
}