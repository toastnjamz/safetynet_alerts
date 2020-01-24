package com.safetynet.alerts.jsonloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.domain.Person;

@Service
public class JsonLoader {

	private List<Person> personList = new ArrayList<Person>();
	
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
				//.build()));
	}
		
//		anyPerson.forEach(p -> personList.add(new Person.PersonBuilder.firstName(p.get("firstName").toString())
//				.lastName(p.get("lastName").toString())
//				.address(p.get("address").toString())
//				.city(p.get("city").toString())
//				.zip(p.get("zip").toString())
//				.phone(p.get("phone").toString())
//				.email(p.get("email").toString())
//				.build()));
//	}
	
	public List<Person> getPersons() {
		return personList;
	}
}
