package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.domain.Person;
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

	public Person getPersonByFirstLastName(String firstName, String lastName) {
		return personRepository.findPerson(firstName, lastName);
	}

	public Person createPerson(Person person) {
		for (Person personInList : personRepository.findAll()) {
			if (personInList.equals(person)) {
				return null;
			}
		}
		return personRepository.createPerson(person);
	}

	public void updatePerson(Person person) {
		if (personRepository.findPerson(person.getFirstName(), person.getLastName()) != null) {
			personRepository.updatePerson(person);
			}
	}
	
	public void deletePerson(String firstName, String lastName) {
		if (personRepository.findPerson(firstName, lastName) != null) {
			personRepository.deletePerson(firstName, lastName);
		}
	}

	public String getPersonAge(String firstName, String lastName) {
		String age = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate currentDate = LocalDate.now();

		if (medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName) != null) {
			MedicalRecord foundRecord = medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName);
			LocalDate birthDate = LocalDate.parse(foundRecord.getBirthdate(), formatter);
			age = Integer.toString(Period.between(birthDate, currentDate).getYears());
		}
		return age;
	}

	public Person getPersonInfo(String firstName, String lastName) {
		Person foundPerson;
		if (personRepository.findPerson(firstName, lastName) != null) {
			foundPerson = new Person(firstName, lastName,
					personRepository.findPerson(firstName, lastName).getAddress(),
					personRepository.findPerson(firstName, lastName).getEmail(),
					getPersonAge(firstName, lastName),
					medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName).getMedications(),
					medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName).getAllergies());
			return foundPerson;
		}
		return null;
	}

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

	public List<Person> getChildrenAtAddress(String address) {
		List<Person> initialPersonList;
		List<Person> adultList = new ArrayList<>();
		List<Person> childrenList = new ArrayList<>();
		List<Person> resultPersonList = new ArrayList<>();

		if (personRepository.findAll().stream().anyMatch(p -> p.getAddress().equals(address))) {
			initialPersonList = getPersonsAtAddress(address);

			for (Person person : initialPersonList) {
				Person formattedPerson = new Person(person.getFirstName(), person.getLastName(),
						(getPersonAge(person.getFirstName(), person.getLastName())));

				int age = Integer.parseInt(getPersonAge(person.getFirstName(), person.getLastName()));
				if (age < 18) {
					childrenList.add(formattedPerson);
				} else {
					adultList.add(formattedPerson);
				}
			}
		}
		if (!childrenList.isEmpty()) {
			resultPersonList.addAll(childrenList);
			resultPersonList.addAll(adultList);
			return resultPersonList;
		} else {
			return childrenList;
		}
	}

	public String getChildrenInList(List<Person> personList) {
		int numChildren = 0;
		for (Person person : personList) {
			if (Integer.parseInt(getPersonAge(person.getFirstName(), person.getLastName())) < 18) {
				numChildren++;
			}
		}
		return Integer.toString(numChildren);
	}

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