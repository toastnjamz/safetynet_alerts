package com.safetynet.alerts.controller;

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

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicatePersonException;
import com.safetynet.alerts.exception.PersonNotFoundException;
import com.safetynet.alerts.service.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping
	public String getAllPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping("/{firstName}/{lastName}")
	public String getPersonByFirstLastName(@PathVariable("firstName") String firstName,
		@PathVariable("lastName") String lastName) throws PersonNotFoundException {
		try {
			return personService.getPersonByFirstLastName(firstName, lastName);
		} catch (PersonNotFoundException e) {
			System.err.println("PersonNotFoundException: " + e.getMessage());
			throw new DuplicatePersonException("Person does not exist");
		}
	}
	
	//TODO: Error handling for sending incomplete data
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String createPerson(@RequestBody Person person) throws DuplicatePersonException {
		try {
			return personService.createPerson(person);
		} catch (DuplicatePersonException e) {
			System.err.println("DuplicatePersonException: " + e.getMessage());
			throw new DuplicatePersonException("Person already exists");
		}
	}
	
	//TODO: Error handling for trying to change firstName or lastName? Sending incomplete data
	@PutMapping("/{firstName}/{lastName}")
	@ResponseStatus(HttpStatus.OK)
	public void updatePerson(@RequestBody Person person, @PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) throws PersonNotFoundException {
		try {
			personService.updatePerson(person);
		} catch (PersonNotFoundException e) {
			System.err.println("PersonNotFoundException: " + e.getMessage());
			throw new DuplicatePersonException("Person does not exist");
		}
	}
	
	@DeleteMapping("/{firstName}/{lastName}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName) throws PersonNotFoundException {
		try {
			personService.deletePerson(firstName, lastName);
		} catch (PersonNotFoundException e) {
			System.err.println("PersonNotFoundException: " + e.getMessage());
			throw new DuplicatePersonException("Person does not exist");
		}
	}
}
