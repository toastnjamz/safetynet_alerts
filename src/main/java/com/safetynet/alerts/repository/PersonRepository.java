package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.jsonloader.JsonLoader;
import com.safetynet.alerts.jsonloader.JsonLoaderEvent;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.domain.Person;

@Repository
public class PersonRepository //implements ApplicationListener<JsonLoaderEvent> 
{ 
	
	private List<Person> personList;
	
	public void onApplicationEvent(JsonLoaderEvent event) {
		JsonLoaderEvent jsonLoaderEvent = (JsonLoaderEvent) event;
		//personList = jsonLoaderEvent.getJsonLoader().getPersons();
	}
	
	public PersonRepository() {
//		String jsonFilePath = "src/main/resources/data.json";
//		byte[] byteArray;
//		try {
//			byteArray = Files.readAllBytes(new File(jsonFilePath).toPath());
//			JsonIterator jsoniter = JsonIterator.parse(byteArray);
//			Any any = jsoniter.readAny();
//			Any anyPerson = any.get("persons");
//			anyPerson.forEach(p -> personList.add(new Person(p.get("firstName").toString(),
//					(p.get("lastName").toString()),
//					(p.get("address").toString()),
//					(p.get("city").toString()),
//					(p.get("zip").toString()),
//					(p.get("phone").toString()),
//					(p.get("email").toString()))));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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