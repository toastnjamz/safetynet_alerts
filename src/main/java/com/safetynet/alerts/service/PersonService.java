package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.repository.PersonRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {
	
	private PersonRepository personRepository;

	private MedicalRecordService medicalRecordService;
	
	@Autowired
	public PersonService(PersonRepository personRepository, MedicalRecordService medicalRecordService) {

		this.personRepository = personRepository;
		this.medicalRecordService = medicalRecordService;
	}
	
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
	public Person getPersonByFirstLastName(String firstName, String lastName) throws NotFoundException {
		if (personRepository.findPerson(firstName, lastName) == null) {
			throw new NotFoundException();
		}
		return personRepository.findPerson(firstName, lastName);
	}
	
	public Person createPerson(Person person) throws DuplicateException {
		for (Person personInList : personRepository.findAll()) {
			if (personInList.equals(person)) {
				throw new DuplicateException();
			}
		}
		return personRepository.createPerson(person);
	}
	
	public void updatePerson(Person person) throws NotFoundException {
		if (personRepository.findPerson(person.getFirstName(), person.getLastName()) == null) {
			throw new NotFoundException();
		}
		personRepository.updatePerson(person);
	}
	
	public void deletePerson(String firstName, String lastName) throws NotFoundException {
		if (personRepository.findPerson(firstName, lastName) == null) {
			throw new NotFoundException();
		}
		personRepository.deletePerson(firstName, lastName);
	}

	public String getPersonAge(String firstName, String lastName) throws NotFoundException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate currentDate = LocalDate.now();

		if (medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName) == null) {
			throw new NotFoundException();
		}
		MedicalRecord foundRecord = medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName);
		LocalDate birthDate = LocalDate.parse(foundRecord.getBirthdate(), formatter);

		return Integer.toString(Period.between(birthDate, currentDate).getYears());
	}

	public Person getPersonInfo(String firstName, String lastName) throws NotFoundException {
		if (personRepository.findPerson(firstName, lastName) == null) {
			throw new NotFoundException();
		}
		Person foundPerson = personRepository.findPerson(firstName, lastName);
		foundPerson.setFirstName(firstName);
		foundPerson.setLastName(lastName);
		foundPerson.setAddress(personRepository.findPerson(firstName, lastName).getAddress());
		foundPerson.setEmail(personRepository.findPerson(firstName, lastName).getEmail());
		foundPerson.setAge(getPersonAge(firstName, lastName));
		foundPerson.setMedications(medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName).getMedications());
		foundPerson.setAllergies(medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName).getAllergies());

		return foundPerson;
	}

	//TODO: Remove if getPersonsAtAddresses() below can be used
	public List<Person> getPersonsAtAddress(String address) {
		List<Person> personList = new ArrayList<>();

		if (personRepository.findAll().stream().anyMatch(p -> p.getAddress().equals(address))) {
			personList = personRepository.findAll().stream().filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());
		}
		return personList;
	}

	public List<Person> getPersonsAtAddresses(List<String> addresses) {
		List<Person> personList = new ArrayList<>();

		for (String address : addresses) {
			personList.addAll(personRepository.findAll().stream().filter(p -> p.getAddress().equals(address)).collect(Collectors.toList()));
		}
		return personList;
	}

	//TODO
	public String getChildrenInList(List<Person> personList) {
		int numChildren = 0;
		for (Person person : personList) {
			if (Integer.parseInt(getPersonAge(person.getFirstName(), person.getLastName())) < 18) {
				numChildren++;
			}
		}
		return Integer.toString(numChildren);
	}

	//TODO
	public String getAdultsInList(List<Person> personList) {
		int numAdults = 0;
		for (Person person : personList) {
			if (Integer.parseInt(getPersonAge(person.getFirstName(), person.getLastName())) >= 18) {
				numAdults++;
			}
		}
		return Integer.toString(numAdults);
	}

	public List<String> getEmailsByCity(String city) {
		List<String> emailList = new ArrayList<>();

		if (personRepository.findAll().stream().anyMatch(p -> p.getCity().equals(city))) {
			List<Person> personList = personRepository.findAll().stream().
					filter(p -> p.getCity().equals(city)).collect(Collectors.toList());
			for (Person person : personList) {
				emailList.add(person.getEmail());
			}
		}
		return emailList;
	}
}