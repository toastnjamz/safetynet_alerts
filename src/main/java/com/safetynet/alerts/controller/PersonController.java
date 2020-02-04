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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicatePersonException;
import com.safetynet.alerts.exception.PersonNotFoundException;
import com.safetynet.alerts.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/person")
public class PersonController {
	
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);

	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping
	public String getAllPersons() {
		log.info("GET called for getAllPersons, SUCCESS");
		return personService.getAllPersons();
	}
	
	@GetMapping("/{firstName}/{lastName}")
	public String getPersonByFirstLastName(@PathVariable("firstName") String firstName,
		@PathVariable("lastName") String lastName) {
		try {
			log.info("GET called for getPersonByFirstLastName, SUCCESS");
			return personService.getPersonByFirstLastName(firstName, lastName);
		} catch (PersonNotFoundException e) {
			log.error("GET called for getPersonByFirstLastName, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found", e);
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String createPerson(@RequestBody Person person) {
		try {
			log.info("GET called for createPerson, SUCCESS");
			return personService.createPerson(person);
		} catch (DuplicatePersonException e) {
			log.error("GET called for createPerson, ERROR");
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Person already exists", e);
		}
	}
	
	@PutMapping("/{firstName}/{lastName}")
	@ResponseStatus(HttpStatus.OK)
	public void updatePerson(@RequestBody Person person, @PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		try {
			log.info("GET called for updatePerson, SUCCESS");
			personService.updatePerson(person);
		} catch (PersonNotFoundException e) {
			log.error("GET called for updatePerson, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person does not exist", e);
		}
	}
	
	@DeleteMapping("/{firstName}/{lastName}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName) {
		try {
			log.info("GET called for deletePerson, SUCCESS");
			personService.deletePerson(firstName, lastName);
		} catch (PersonNotFoundException e) {
			log.error("GET called for deletePerson, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person does not exist", e);
		}
	}
}
