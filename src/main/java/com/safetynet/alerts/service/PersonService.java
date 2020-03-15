package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Person;
import java.util.List;

public interface PersonService {
	
	public List<Person> getAllPersons();

	public Person getPersonByFirstLastName(String firstName, String lastName);

	public Person createPerson(Person person);

	public void updatePerson(Person person);
	
	public void deletePerson(String firstName, String lastName);

	public String getPersonAge(String firstName, String lastName);

	public Person getPersonInfo(String firstName, String lastName);

	public List<Person> getPersonsAtAddress(String address);

	public List<Person> getPersonsAtAddresses(List<String> addresses);

	public List<Person> getChildrenAtAddress(String address);

	public String getChildrenInList(List<Person> personList);

	public String getAdultsInList(List<Person> personList);

	public List<String> getEmailsByCity(String city);
}