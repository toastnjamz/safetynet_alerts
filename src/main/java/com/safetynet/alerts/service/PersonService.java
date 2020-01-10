package com.safetynet.alerts.service;

import org.springframework.stereotype.Service;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicatePersonException;
import com.safetynet.alerts.exception.PersonNotFoundException;
import com.safetynet.alerts.repository.PersonRepository;


@Service
public class PersonService {
	private PersonRepository personRepository;
	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public String getAllPersons() {
		return JsonStream.serialize(personRepository.findAll());
	}
	
	public String getPersonByFirstLastName(String firstName, String lastName) throws PersonNotFoundException {
		if (personRepository.findPerson(firstName, lastName) == null) {
			throw new PersonNotFoundException("PersonNotFoundError from PersonService");
		}
		return JsonStream.serialize(personRepository.findPerson(firstName, lastName));
	}
	
	//TODO: Fix error handing (return correct error and response code)
	public String createPerson(Person person) throws DuplicatePersonException {
		for (Person personInList : personRepository.findAll()) {
			if (personInList.equals(person)) {
				throw new DuplicatePersonException("DuplicatePersonError from PersonService");
			}
		}
		return JsonStream.serialize(personRepository.createPerson(person));
	}
	
	public void updatePerson(Person person) throws PersonNotFoundException {
		for (Person personInList : personRepository.findAll()) {
			if (personInList.equals(person)) {
				personRepository.updatePerson(person);
			}
			throw new PersonNotFoundException("PersonNotFoundError from PersonService");
		}
	}
	
	public void deletePerson(String firstName, String lastName) throws PersonNotFoundException {
		if (personRepository.findPerson(firstName, lastName) == null) {
			throw new PersonNotFoundException("PersonNotFoundError from PersonService");
		}
		personRepository.deletePerson(firstName, lastName);
	}

}
