package com.safetynet.alerts.jsonloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Component
public class JsonLoader {

	@Autowired
	private PersonRepository personRepository;

	@EventListener
	public void onApplicationReadyEvent(ApplicationReadyEvent event) throws IOException {
		String jsonFilePath = "src/main/resources/data.json";
		byte[] byteArray;
		byteArray = Files.readAllBytes(new File(jsonFilePath).toPath());

		JsonIterator jsoniter = JsonIterator.parse(byteArray);
		// Any is a lazy container in Jsoniter that can hold different values
		Any any = jsoniter.readAny();
		Any anyPerson = any.get("persons");
		anyPerson.forEach(p -> personRepository.createPerson(new Person(p.get("firstName").toString(),
				(p.get("lastName").toString()),
				(p.get("address").toString()),
				(p.get("city").toString()),
				(p.get("zip").toString()),
				(p.get("phone").toString()),
				(p.get("email").toString()))));
	}
}
