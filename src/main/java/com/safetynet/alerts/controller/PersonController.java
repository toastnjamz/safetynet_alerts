package com.safetynet.alerts.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
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
		return JsonStream.serialize(personService.getAllPersons());
	}
	
	@GetMapping("/person/{firstName}/{lastName}")
	public String getPersonByFirstLastName(@PathVariable("firstName") String firstName,
		@PathVariable("lastName") String lastName) {
		log.info("GET request made for getPersonByFirstLastName: " + firstName + " " + lastName);
		try {
			log.info("GET called for getPersonByFirstLastName, SUCCESS");
			return JsonStream.serialize(personService.getPersonByFirstLastName(firstName, lastName));
		} catch (NotFoundException e) {
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
			return JsonStream.serialize(personService.createPerson(person));
		} catch (DuplicateException e) {
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
		} catch (NotFoundException e) {
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
		} catch (NotFoundException e) {
			log.error("DELETE called for deletePerson, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person does not exist", e);
		}
	}

	@GetMapping("/personInfo")
	public String getPersonInfo(@RequestParam("firstName") String firstName,
								@RequestParam("lastName") String lastName) {
		log.info("GET request made for getPersonInfo: " + firstName + " " + lastName);
		try {
			log.info("GET called getPersonInfo, SUCCESS");
			return JsonStream.serialize(personService.getPersonInfo(firstName, lastName));
		} catch (NotFoundException e) {
			log.error("GET called for getPersonInfo, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found", e);
		}
	}

	@GetMapping("/communityEmail")
	public String getPersonsEmailsByCity(@RequestParam("city") String city) {
		log.info("GET request made for getPersonsEmailsByCity: " + city);
		try {
			log.info("GET called getPersonsEmailsByCity, SUCCESS");
			return JsonStream.serialize(personService.getEmailsByCity(city));
		} catch (NotFoundException e) {
			log.error("GET called for getPersonsEmailsByCity, ERROR");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
		}
	}
}