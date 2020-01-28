package com.safetynet.alerts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicatePersonException;
import com.safetynet.alerts.exception.PersonNotFoundException;
import com.safetynet.alerts.repository.PersonRepository;


@Service
public class PersonService {
	
	//@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public String getAllPersons() {
		return JsonStream.serialize(personRepository.findAll());
	}
	
	public String getPersonByFirstLastName(String firstName, String lastName) throws PersonNotFoundException {
		if (personRepository.findPerson(firstName, lastName) == null) {
			throw new PersonNotFoundException();
		}
		return JsonStream.serialize(personRepository.findPerson(firstName, lastName));
	}
	
	public String createPerson(Person person) throws DuplicatePersonException {
		for (Person personInList : personRepository.findAll()) {
			if (personInList.equals(person)) {
				throw new DuplicatePersonException();
			}
		}
		return JsonStream.serialize(personRepository.createPerson(person));
	}
	
	public void updatePerson(Person person) throws PersonNotFoundException {
		if (personRepository.findPerson(person.getFirstName(), person.getLastName()) == null) {
			throw new PersonNotFoundException();
			}
		personRepository.updatePerson(person);
	}
	
	public void deletePerson(String firstName, String lastName) throws PersonNotFoundException {
		if (personRepository.findPerson(firstName, lastName) == null) {
			throw new PersonNotFoundException();
		}
		personRepository.deletePerson(firstName, lastName);
	}
}
