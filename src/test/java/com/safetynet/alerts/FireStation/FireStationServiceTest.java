package com.safetynet.alerts.FireStation;

import com.safetynet.alerts.domain.*;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.FireStationService;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class FireStationServiceTest {

    private FireStationRepository fireStationRepository;
    private FireStationService fireStationService;
    private PersonRepository personRepository;
    private PersonService personService;
    private MedicalRecordService medicalRecordService;
    private MedicalRecordRepository medicalRecordRepository;
    private FireStation fireStation1;
    private FireStation fireStation2;
    private Person person1;
    private Person person2;
    private Person person3;
    private MedicalRecord medicalRecord1;
    private MedicalRecord medicalRecord2;
    private MedicalRecord medicalRecord3;

    @Before
    public void setup() {
        personRepository = new PersonRepository();
        medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        personService = new PersonService(personRepository, medicalRecordService);
        fireStationRepository = new FireStationRepository();
        fireStationService = new FireStationService(fireStationRepository, personService, medicalRecordService);

        fireStation1 = new FireStation("123 Market Street", "9");
        fireStation2 = new FireStation("1 Grand Palace", "10");

        fireStationService.createFireStation(fireStation1);
        fireStationService.createFireStation(fireStation2);

        person1 = new Person("Duncan", "Idaho", "123 Market Street", "Giedi Prime",
                "12345", "444-444-4444", "duncan@gmail.com");
        person2 = new Person("Jessica", "Atreides", "1 Grand Palace", "Arrakeen", "12345",
                "555-555-5555", "benegesserit@yahoo.com");
        person3 = new Person("Paul", "Atreides", "1 Grand Palace", "Caladan",
                "12345", "555-555-5555", "maud-dib@gmail.com");

        personService.createPerson(person1);
        personService.createPerson(person2);
        personService.createPerson(person3);

        List<String> medicationsList1 = new ArrayList<>(); { medicationsList1.add("medication1"); }
        List<String> medicationsList2 = new ArrayList<>(); { medicationsList2.add("medication2"); }
        List<String> medicationsList3 = new ArrayList<>();
        List<String> allergiesList1 = new ArrayList<>(); { allergiesList1.add("allergy1"); }
        List<String> allergiesList2 = new ArrayList<>(); { allergiesList2.add("allergy2"); allergiesList2.add("allergy3"); }
        List<String> allergiesList3 = new ArrayList<>();


        medicalRecord1 = new MedicalRecord("Duncan", "Idaho", "01/01/1960", medicationsList1,
                allergiesList1);
        medicalRecord2 = new MedicalRecord("Jessica", "Atreides", "01/01/1970", medicationsList2,
                allergiesList2);
        medicalRecord3 = new MedicalRecord("Paul", "Atreides", "01/01/2010", medicationsList3,
                allergiesList3);

        medicalRecordService.createMedicalRecord(medicalRecord1);
        medicalRecordService.createMedicalRecord(medicalRecord2);
        medicalRecordService.createMedicalRecord(medicalRecord3);
    }

    @Test
    public void getAllFireStations_repositoryHasData_allDataReturned() {
        // arrange

        // act
        List<FireStation> result = fireStationService.getAllFireStations();

        // assert
        assertEquals(result.size(), 2);
        assertTrue(result.get(0).getStation().equals("9"));
    }

    @Test
    public void getFireStationByAddress_fireStationExists_fireStationDataReturned() {
        // arrange

        // act
        FireStation result = fireStationService.getFireStationByAddress("123 Market Street");

        // assert
        assertTrue(result.equals(fireStation1));
    }

    @Test
    public void createFireStation_addingValidFireStation_fireStationDataReturned() {
        // arrange
        FireStation fireStation3 = new FireStation("123 Test Street", "11");

        // act
        FireStation result = fireStationService.createFireStation(fireStation3);

        // assert
        assertTrue(result.equals(fireStation3));
    }

    @Test
    public void updateFireStation_updatingValidFireStation_fireStationDataUpdated() {
        // arrange
        assertEquals("9", fireStation1.getStation());

        FireStation updatedFireStation1 = new FireStation("123 Market Street", "0");

        // act
        fireStationService.updateFireStation(updatedFireStation1);

        // assert
        assertEquals(fireStation1.getStation(), "0");
    }

    @Test
    public void deleteFireStation_fireStationExists_fireStationDataDeleted() {
        // arrange
        FireStation fireStation3 = new FireStation("456 Testington Manor", "12");

        FireStation fireStationCreated = fireStationService.createFireStation(fireStation3);
        assertEquals(3, fireStationRepository.findAll().size());

        // act
        fireStationService.deleteFireStation("456 Testington Manor");

        // assert
        assertEquals(2, fireStationRepository.findAll().size());
    }

    @Test
    public void getAddressesByStationNumber_fireStationExists_addressDataReturned() {
        // arrange
        FireStation fireStation3 = new FireStation("456 Testington Manor", "9");
        fireStationService.createFireStation(fireStation3);

        // act
        List<String> result = fireStationService.getAddressesByStationNumber("9");

        // assert
        assertEquals(result.size(), 2);
        assertEquals(result.get(1), "456 Testington Manor");
    }

    @Test
    public void getPersonsByStationNumber_fireStationExists_personDataReturned() {
        // arrange

        // act
        List<Person> result = fireStationService.getPersonsByStationNumber("9");

        // assert
        assertEquals(result.get(0).getFirstName(), "Duncan");
    }

    @Test
    public void getListAdultsAndChildrenByStationNumber_fireStationExists_personDataReturned() {
        // arrange

        // act
        AdultsAndChildrenServicedByStation result = fireStationService.getListAdultsAndChildrenByStationNumber("10");

        // assert
        //assertEquals(result.getFormattedPersonList().size(), 2);
        assertEquals(result.getNumberOfChildren(), "1");
        assertEquals(result.getNumberOfAdults(), "1");

    }

    @Test
    public void getStationAndPersonsByAddress_addressExists_stationAndPersonDataReturned() {
        // arrange

        // act
        HashMap<String, List<Person>> resultsMap = fireStationService.getStationAndPersonsByAddress("123 Market Street");

        // assert
        assertEquals("Duncan", resultsMap.get("9").get(0).getFirstName());
    }

    @Test
    public void getHouseholdsByStationList_stationListValid_householdDataReturned() {
        // arrange
        List<String> stationList = new ArrayList<>();
        stationList.add("9");
        stationList.add("10");

        // act
        List<Household> result = fireStationService.getHouseholdsByStationList(stationList);

        // assert
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getAddress(), "123 Market Street");
    }

    @Test
    public void getPhoneNumbersByStationNumber_fireStationExists_phoneDataReturned() {
        // arrange

        // act
        List<String> result = fireStationService.getPhoneNumbersByStationNumber("9");

        // assert
        assertEquals(result.get(0),"444-444-4444");
    }
}
