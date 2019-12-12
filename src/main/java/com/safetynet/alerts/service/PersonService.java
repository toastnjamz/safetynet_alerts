package com.safetynet.alerts.service;

import org.springframework.stereotype.Service;

import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService {
	private PersonRepository personRepository;
	
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

}
