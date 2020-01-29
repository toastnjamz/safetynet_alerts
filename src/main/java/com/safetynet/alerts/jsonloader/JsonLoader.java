package com.safetynet.alerts.jsonloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.domain.Person;

@Service
public class JsonLoader implements ApplicationEventPublisherAware {

	private List<Person> personList = new ArrayList<Person>();
	
	private ApplicationEventPublisher publisher;
	
	// Constructor deserializes data from JSON file
	public JsonLoader() throws IOException {
		String jsonFilePath = "src/main/resources/data.json";
		byte[] byteArray;
		byteArray = Files.readAllBytes(new File(jsonFilePath).toPath());

		JsonIterator jsoniter = JsonIterator.parse(byteArray);
		// Any is a lazy container in Jsoniter that can hold different values
		Any any = jsoniter.readAny();
		Any anyPerson = any.get("persons");
		anyPerson.forEach(p -> personList.add(new Person(p.get("firstName").toString(),
				(p.get("lastName").toString()),
				(p.get("address").toString()),
				(p.get("city").toString()),
				(p.get("zip").toString()),
				(p.get("phone").toString()),
				(p.get("email").toString()))));
		
		
		publisher.publishEvent(new JsonLoaderEvent(this, "Loading JSON", this));
	}
		
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	public List<Person> getPersons() {
		return personList;
	}
}
