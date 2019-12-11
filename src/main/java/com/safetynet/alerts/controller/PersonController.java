package com.safetynet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.safetynet.alerts.service.PersonService;

@Controller
public class PersonController {
	
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	

}
