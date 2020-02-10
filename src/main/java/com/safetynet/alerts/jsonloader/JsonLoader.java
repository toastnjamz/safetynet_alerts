package com.safetynet.alerts.jsonloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
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

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private FireStationRepository fireStationRepository;

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

		Any anyMedical = any.get("medicalrecords");
		anyMedical.forEach(m -> medicalRecordRepository.createMedicalRecord(new MedicalRecord(m.get("firstName").toString(),
				(m.get("lastName").toString()),
//				(m.get("birthdate").toString()),
				(m.get("birthdate").toString()))));
//				(m.get("medications").toString())),
//				(m.get("allergies").asList())));

//		List<Any> anyMedications = (List<Any>) any.get("medications");
//		List<Any> anyMedications = jsoniter.readAny().asList();
//		anyMedications.forEach(m -> m.toString());
//
//		Any anyAllergies = any.get("allergies");
//		anyAllergies.forEach(a -> a.toString());

		Any anyFireStation = any.get("firestations");
		anyFireStation.forEach(f -> fireStationRepository.createStation(new FireStation(f.get("address").toString(),
				(f.get("station").toString()))));
	}
}
