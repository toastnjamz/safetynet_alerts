package com.safetynet.alerts.jsonloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.jsoniter.spi.TypeLiteral;
import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;

@Component
public class JsonLoader {

	private static final Logger log = LoggerFactory.getLogger(JsonLoader.class);

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private FireStationRepository fireStationRepository;

	@EventListener
	public void onApplicationReadyEvent(ApplicationReadyEvent event) {
		try {
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

			//Diagnostic debug out to confirm persons have been loaded from JSON
			log.debug("Persons loaded from JSON file");

			Any medicalAny = any.get("medicalrecords");
			medicalAny.forEach(medicalRecord -> medicalRecordRepository.createMedicalRecord(new MedicalRecord(medicalRecord.get("firstName").toString(),
					medicalRecord.get("lastName").toString(),
					medicalRecord.get("birthdate").toString(),
					medicalRecord.get("medications").as(new TypeLiteral<List<String>>(){}),
					medicalRecord.get("allergies").as(new TypeLiteral<List<String>>(){}))));

			//Diagnostic debug out to confirm medical records have been loaded from JSON
			log.debug("Medical Records loaded from JSON file");

			Any anyFireStation = any.get("firestations");
			anyFireStation.forEach(f -> fireStationRepository.createStation(new FireStation(f.get("address").toString(),
					(f.get("station").toString()))));

			//Diagnostic debug out to confirm fire stations have been loaded from JSON
			log.debug("Fire Stations loaded from JSON file");

		}
		catch (IOException e) {
			log.error("ERROR loading JSON file");
		}
	}
}
