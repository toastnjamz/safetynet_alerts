package com.safetynet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;

import net.minidev.json.JSONObject;

@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("/person")
	public JSONObject getAllPersons() {
		personService.getAllPersons();
		return null;
	}
	
	@PostMapping("/person")
	public JSONObject createPerson(Person person) {
		personService.createPerson(person);
		return null;
	}
	
	@PutMapping("/person")
	public JSONObject updatePerson(String firstAndLastName) {
		personService.updatePerson(firstAndLastName);
		return null;
	}
	
	@DeleteMapping("/person")
	public void deletePerson(String firstAndLastName) {
		personService.deletePerson(firstAndLastName);
	}
}
