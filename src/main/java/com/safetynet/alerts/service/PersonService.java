package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicatePersonException;
import com.safetynet.alerts.exception.PersonNotFoundException;
import com.safetynet.alerts.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {
	
	private PersonRepository personRepository;

//	private MedicalRecordRepository medicalRecordRepository;
	
//	@Autowired
//	public PersonService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
//
//		this.personRepository = personRepository;
//		this.medicalRecordRepository = medicalRecordRepository;
//	}

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

//	//TODO
//	public String getPersonAge(String firstName, String lastName) throws PersonNotFoundException {
//		String age = "";
//
//		if (personRepository.findPerson(firstName, lastName) == null) {
//			throw new PersonNotFoundException();
//		}
//		Person person = findPerson(person.getFirstName(), person.getLastName());
//
//		return age;
//	}
//
//	//TODO
//	public String getPersonInfo(String firstName, String lastName) throws PersonNotFoundException {
//		if (personRepository.findPerson(firstName, lastName) == null) {
//			throw new PersonNotFoundException();
//		}
//		Person person = findPerson(person.getFirstName(), person.getLastName());
//
//		return age;
//	}

	public String getEmailsByCity(String city) {
		List<String> emailList = new ArrayList<>();

		if (personRepository.findAll().stream().anyMatch(p -> p.getCity().equals(city))) {
			List<Person> personList = personRepository.findAll().stream().
					filter(p -> p.getCity().equals(city)).collect(Collectors.toList());
			for (Person person : personList) {
				emailList.add(person.getEmail());
			}
		}
		return JsonStream.serialize(emailList);
	}
}