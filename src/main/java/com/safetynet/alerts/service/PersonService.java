package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;

import net.minidev.json.JSONObject;

@Service
public class PersonService {
	private PersonRepository personRepository;
	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

//	public List<Person> getAllPersons() {
//		return personRepository.findAll();
//	}
	
	public String getAllPersons() {
		return JsonStream.serialize(personRepository.findAll());
	}
	
	public void createPerson(Person person) {
		personRepository.createPerson(person);
		
	}
	
	public void updatePerson(String firstAndLastName) {
		personRepository.updatePerson(firstAndLastName);
		
	}
	
	public void deletePerson(String firstAndLastName) {
		personRepository.deletePerson(firstAndLastName);
	}

}
