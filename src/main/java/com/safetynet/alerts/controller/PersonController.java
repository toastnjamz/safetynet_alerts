package com.safetynet.alerts.controller;

import com.jsoniter.output.JsonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Optional;


@RestController
public class PersonController {
	
	private static final Logger log = LoggerFactory.getLogger(PersonController.class);

	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping("/persons")
	public String getAllPersons() {
		log.info("HTTP GET request received for getAllPersons");
		return JsonStream.serialize(personService.getAllPersons());
	}

	@GetMapping("/person")
	public String getPersonByFirstLastName(@NotNull @RequestParam String firstName,
										   @NotNull @RequestParam String lastName,
										   HttpServletResponse response) {
		log.debug("HTTP GET request received for getPersonByFirstLastName: {} {}", firstName, lastName);
		Optional<Person> personOptional = Optional.ofNullable(personService.getPersonByFirstLastName(firstName, lastName));
		if (personOptional.isPresent()) {
			log.info("HTTP GET request received for getPersonByFirstLastName, SUCCESS");
			return JsonStream.serialize(personService.getPersonByFirstLastName(firstName, lastName));
		}
		else {
			log.error("HTTP GET request received for getPersonByFirstLastName, ERROR");
			response.setStatus(404);
			return String.format("Person with name %s %s does not exist", firstName, lastName);
		}
	}

	@PostMapping("/person")
	public String createPerson(@NotNull @RequestBody Person person,
							   HttpServletResponse response) {
		log.debug("HTTP POST request received for createPerson: {} {}", person.getFirstName(), person.getLastName());
		Optional<Person> personOptional = Optional.ofNullable(personService.getPersonByFirstLastName(person.getFirstName(), person.getLastName()));
		if (!personOptional.isPresent()) {
			log.info("HTTP POST request received for createPerson, SUCCESS");
			response.setStatus(201);
			return JsonStream.serialize(personService.createPerson(person));
		}
		else {
			log.error("HTTP POST request received for createPerson, ERROR");
			response.setStatus(409);
			return String.format("Person %s %s already exists", person.getFirstName(), person.getLastName());
		}
	}

	@PutMapping("/person")
	public void updatePerson(@NotNull @RequestBody Person person,
							 @NotNull @RequestParam String firstName,
							 @NotNull @RequestParam String lastName,
							 HttpServletResponse response) {
		log.debug("HTTP PUT request received for updatePerson: {} {}", firstName, lastName);
		Optional<Person> personOptional = Optional.ofNullable(personService.getPersonByFirstLastName(person.getFirstName(), person.getLastName()));
		if (personOptional.isPresent()) {
			log.info("HTTP PUT request received for updatePerson, SUCCESS");
			response.setStatus(200);
			personService.updatePerson(person);
		} else {
			log.error("HTTP PUT request received for updatePerson, ERROR");
			response.setStatus(404);
		}
	}

	@DeleteMapping("/person")
	public void deletePerson(@NotNull @RequestParam String firstName,
							 @NotNull @RequestParam String lastName,
							 HttpServletResponse response) {
		log.debug("HTTP DELETE request received for deletePerson: {} {}", firstName, lastName);
		Optional<Person> personOptional = Optional.ofNullable(personService.getPersonByFirstLastName(firstName, lastName));
		if (personOptional.isPresent()) {
			log.info("HTTP DELETE request received for deletePerson, SUCCESS");
			response.setStatus(200);
			personService.deletePerson(firstName, lastName);
		} else {
			log.error("HTTP DELETE request received for deletePerson, ERROR");
			response.setStatus(404);
		}
	}

	@GetMapping("/personInfo")
	public String getPersonInfo(@NotNull @RequestParam String firstName,
								@NotNull @RequestParam String lastName,
								HttpServletResponse response) {
		log.debug("HTTP GET request received for getPersonInfo: {} {}", firstName, lastName);
		Optional<Person> personOptional = Optional.ofNullable(personService.getPersonByFirstLastName(firstName, lastName));
		if (personOptional.isPresent()) {
			log.info("HTTP GET request received getPersonInfo, SUCCESS");
			return JsonStream.serialize(personService.getPersonInfo(firstName, lastName));
		} else {
			log.error("HTTP GET request received for getPersonInfo, ERROR");
			response.setStatus(404);
			return String.format("Person with name %s %s does not exist", firstName, lastName);
		}
	}

	@GetMapping("/childAlert")
	public String getChildrenAtAddress(@NotNull @RequestParam String address,
									   HttpServletResponse response) {
		log.debug("HTTP GET request received for getChildrenAtAddress: {}", address);
		if (!personService.getChildrenAtAddress(address).isEmpty()) {
			log.info("HTTP GET request received for getChildrenAtAddress, SUCCESS");
			return JsonStream.serialize(personService.getChildrenAtAddress(address));
		} else {
			log.error("HTTP GET request received for getChildrenAtAddress, ERROR");
			response.setStatus(404);
			//return String.format("There are no children at %s", address);
			return String.format("");
		}
	}

	@GetMapping("/communityEmail")
	public String getPersonsEmailsByCity(@NotNull @RequestParam String city,
										 HttpServletResponse response) {
		log.debug("HTTP GET request received for getPersonsEmailsByCity: {}", city);
		if (!personService.getEmailsByCity(city).isEmpty()) {
			log.info("HTTP GET request received getPersonsEmailsByCity, SUCCESS");
			return JsonStream.serialize(personService.getEmailsByCity(city));
		} else {
			log.error("HTTP GET request received for getPersonsEmailsByCity, ERROR");
			response.setStatus(404);
			return String.format("No emails were found for %s", city);
		}
	}
}