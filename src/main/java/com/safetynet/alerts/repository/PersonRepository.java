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
	private JsonLoader jsonLoader;
	
	@Autowired
	public PersonRepository(JsonLoader jsonLoader) {
		this.jsonLoader = jsonLoader;
		this.personList = jsonLoader.getPersons();
	}
	
	public List<Person> findAll() {
		return personList;
	}

	public Person findPerson(String firstLastName) {
		for (Person person : personList) {
			if (person.getFirstLastName().equals(firstLastName)) {
				return person;
			}
		}
		return null;
	}
	
	public Person createPerson(Person person) {
		personList.add(person);
		return person;
	}

	public void updatePerson(String firstAndLastName) {
		//TODO	
	}

	public void deletePerson(String firstAndLastName) {
		//TODO
	}
}