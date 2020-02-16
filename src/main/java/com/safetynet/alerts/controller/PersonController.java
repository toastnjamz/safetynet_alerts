package com.safetynet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicatePersonException;
import com.safetynet.alerts.exception.PersonNotFoundException;
import com.safetynet.alerts.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class PersonController {
	
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);

	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("/person")
	public String getAllPersons() {
		log.info("GET request made for getAllPersons");
		return personService.getAllPersons();
	}
	
	@GetMapping("/person/{firstName}/{lastName}")
	public String getPersonByFirstLastName(@PathVariable("firstName") String firstName,
		@PathVariable("lastName") String lastName) {
		log.info("GET request made for getPersonByFirstLastName: " + firstName + " " + lastName);
		try {
			log.info("GET called for getPersonByFirstLastName, SUCCESS");
			return personService.getPersonByFirstLastName(firstName, lastName);
		} catch (PersonNotFoundException e) {
			log.error("GET called for getPersonByFirstLastName, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found", e);
		}
	}
	
	@PostMapping("/person")
	@ResponseStatus(HttpStatus.CREATED)
	public String createPerson(@RequestBody Person person) {
		log.info("POST request made for createPerson: " + person.getFirstName() + " " + person.getLastName());
		try {
			log.info("POST called for createPerson, SUCCESS");
			return personService.createPerson(person);
		} catch (DuplicatePersonException e) {
			log.error("POST called for createPerson, ERROR");
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Person already exists", e);
		}
	}
	
	@PutMapping("/person/{firstName}/{lastName}")
	@ResponseStatus(HttpStatus.OK)
	public void updatePerson(@RequestBody Person person, @PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		log.info("PUT request made for updatePerson: " + firstName + " " + lastName);
		try {
			log.info("PUT called for updatePerson, SUCCESS");
			personService.updatePerson(person);
		} catch (PersonNotFoundException e) {
			log.error("PUT called for updatePerson, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person does not exist", e);
		}
	}
	
	@DeleteMapping("/person/{firstName}/{lastName}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePerson(@PathVariable("firstName") String firstName, 
			@PathVariable("lastName") String lastName) {
		log.info("DELETE request made for deletePerson: " + firstName + " " + lastName);
		try {
			log.info("DELETE called for deletePerson, SUCCESS");
			personService.deletePerson(firstName, lastName);
		} catch (PersonNotFoundException e) {
			log.error("DELETE called for deletePerson, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person does not exist", e);
		}
	}

	//TODO
	@GetMapping("/personInfo")
	public String getPersonInfo(@RequestParam("firstName") String firstName,
								@RequestParam("lastName") String lastName) {
		log.info("GET request made for getPersonInfo: " + firstName + " " + lastName);
		try {
			log.info("GET called getPersonInfo, SUCCESS");
			return personService.getPersonInfo(firstName, lastName);
		} catch (PersonNotFoundException e) {
			log.error("GET called for getPersonInfo, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found", e);
		}
	}

	@GetMapping("/communityEmail")
	public String getPersonsEmailsByCity(@RequestParam("city") String city) {
		log.info("GET request made for getPersonsEmailsByCity: " + city);
		try {
			log.info("GET called getPersonsEmailsByCity, SUCCESS");
			return personService.getEmailsByCity(city);
		} catch (PersonNotFoundException e) {
			log.error("GET called for getPersonsEmailsByCity, ERROR");
			//TODO is this ok to do, or do I need to create a new exception for city?
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
		}
	}
}