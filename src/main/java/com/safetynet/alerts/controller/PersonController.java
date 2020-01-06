package com.safetynet.alerts.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import com.jsoniter.annotation.JsonObject;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
//	@GetMapping("/person")
//	public JSONObject getAllPersons() {
//		personService.getAllPersons();
//		return null;
//	}
	
	@GetMapping
	public String getAllPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping("/{firstLastName}")
	public String getPersonByFirstLastName(@PathVariable(value = "firstLastName") String firstLastName) {
		return personService.getPersonByFirstLastName(firstLastName);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public String createPerson(@Valid @RequestBody Person person) {
		return personService.createPerson(person);
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
