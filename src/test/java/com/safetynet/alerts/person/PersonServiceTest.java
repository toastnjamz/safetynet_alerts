package com.safetynet.alerts.person;

import static org.junit.Assert.*;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.MedicalRecordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

	private PersonRepository personRepository;
	private MedicalRecordService medicalRecordService;
	private MedicalRecordRepository medicalRecordRepository;
	private PersonService personService;
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

		person1 = new Person("Duncan", "Idaho", "123 Market Street", "Giedi Prime",
				"12345", "555-555-5555", "duncan@gmail.com");
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
	public void getAllPersons_repositoryHasData_allDataReturned() {
		// arrange

		// act
        List<Person> result = personService.getAllPersons();

		// assert
		assertEquals(result.size(), 3);
        assertTrue(result.get(0).getFirstName().equals("Duncan"));
	}

	@Test
	public void getPersonByFirstLastName_personExists_personDataReturned() {
		// arrange

		// act
        Person result = personService.getPersonByFirstLastName("Duncan", "Idaho");

		// assert
        assertTrue(result.equals(person1));
	}

	@Test
	public void getPersonByFirstLastName_personDoesNotExist_nullReturned() {
		// arrange

		// act
		Person result = personService.getPersonByFirstLastName("Test", "Person");

		// assert
		assertNull(result);
	}

	@Test
	public void createPerson_addingValidPerson_personDataReturned() {
		// arrange
		Person person4 = new Person("Gurney", "Halleck", "123 Grand Palace", "Caladan",
				"12345", "555-555-5555", "warrior.minstrel@palace.edu");

		// act
        Person result = personService.createPerson(person4);;

		// assert
        assertTrue(result.equals(person4));
	}

	@Test
	public void createPerson_addingExistingPerson_nullReturned() {
		// arrange
		Person duplicatePerson = new Person("Duncan", "Idaho", "123 Market Street", "Giedi Prime",
				"12345", "555-555-5555", "duncan@gmail.com");

		// act
		Person result = personService.createPerson(duplicatePerson);;

		// assert
		assertNull(result);
	}

	@Test
	public void updatePerson_updatingValidPerson_personDataUpdated() {
		// arrange
        assertEquals("123 Market Street", person1.getAddress());

        Person updatedPerson1 = new Person("Duncan", "Idaho", "456 Sietch Tabr", "Arrakis",
				"67899", "666-666-6666", "shaihulud@gmail.com");

		// act
		personService.updatePerson(updatedPerson1);

		// assert
        assertEquals("456 Sietch Tabr", person1.getAddress());
	}

	@Test
	public void updatePerson_updatingInvalidPerson_nothingHappens() {
		// arrange
		Person invalidPerson = new Person("Test", "Person", "456 Sietch Tabr", "Arrakis",
				"67899", "666-666-6666", "shaihulud@gmail.com");

		// act
		personService.updatePerson(invalidPerson);

		// assert
		assertNull(personService.getPersonByFirstLastName(invalidPerson.getFirstName(), invalidPerson.getLastName()));
	}

	@Test
	public void deletePerson_personExists_personDataDeleted() {
		// arrange
		Person person4 = new Person("Gurney", "Halleck", "123 Grand Palace", "Caladan",
				"12345", "555-555-5555", "warrior.minstrel@palace.edu");

		Person personCreated = personService.createPerson(person4);
		assertEquals(4, personRepository.findAll().size());

		// act
		personService.deletePerson("Gurney", "Halleck");

		// assert
		assertEquals(3, personRepository.findAll().size());
	}

	@Test
	public void deletePerson_personDoesNotExist_nothingHappens() {
		// arrange
		assertEquals(3, personRepository.findAll().size());

		// act
		personService.deletePerson("Test", "Person");

		// assert
		assertEquals(3, personRepository.findAll().size());
	}

    @Test
    public void getPersonAge_personExists_personAgeReturned() {
        // arrange

        // act
		String age = personService.getPersonAge(person1.getFirstName(), person1.getLastName());

        // assert
		assertEquals("60", age);
    }

	@Test
	public void getPersonAge_personDoesNotExists_nullReturned() {
		// arrange

		// act
		String age = personService.getPersonAge("Test", "Person");

		// assert
		assertNull(age);
	}

    @Test
    public void getPersonInfo_personExists_personInfoReturned() {
        // arrange

        // act
		Person result = personService.getPersonInfo(person1.getFirstName(), person1.getLastName());

        // assert
		assertTrue(result.equals(person1));
    }

	@Test
	public void getPersonInfo_personDoesNotExist_nullReturned() {
		// arrange

		// act
		Person result = personService.getPersonInfo("Test", "Person");

		// assert
		assertNull(result);
	}

    @Test
    public void getPersonsAtAddress_validAddress_personListReturned() {
        // arrange
		String address = "123 Market Street";

		// act
		List<Person> result = personService.getPersonsAtAddress(address);

        // assert
		assertEquals(result.size(), 1);
		assertTrue(result.get(0).getFirstName().equals("Duncan"));
    }

	@Test
	public void getPersonsAtAddress_invalidAddress_emptyListReturned() {
		// arrange
		String address = "1 Imaginary Street";

		// act
		List<Person> result = personService.getPersonsAtAddress(address);

		// assert
		assertEquals(result.size(), 0);
	}

    @Test
    public void getPersonsAtAddresses_validAddresses_personListReturned() {
        // arrange
		List<String> addressList = new ArrayList<>(); { addressList.add("123 Market Street"); addressList.add("1 Grand Palace"); }

		// act
		List<Person> result = personService.getPersonsAtAddresses(addressList);

        // assert
		assertTrue(result.size() == 3);
		assertTrue(result.get(0).getFirstName().equals("Duncan"));
		assertTrue(result.get(1).getFirstName().equals("Jessica"));
    }

	@Test
	public void getPersonsAtAddresses_invalidAddresses_emptyListReturned() {
		// arrange
		List<String> addressList = new ArrayList<>(); { addressList.add("1 Imaginary Street"); addressList.add("2 Imaginary Street"); }

		// act
		List<Person> result = personService.getPersonsAtAddresses(addressList);

		// assert
		assertTrue(result.size() == 0);
	}

    @Test
    public void getChildrenAtAddress_validAddress_personListReturned() {
        // arrange

        // act
		List<Person> result = personService.getChildrenAtAddress("1 Grand Palace");

        // assert
		assertEquals(result.size(), 2);
		assertTrue(result.get(0).getFirstName().equals("Paul"));
    }

	@Test
	public void getChildrenAtAddress_invalidAddress_emptyListReturned() {
		// arrange

		// act
		List<Person> result = personService.getChildrenAtAddress("1 Imaginary Street");

		// assert
		assertEquals(result.size(), 0);
	}

    @Test
    public void getChildrenInList_validList_numberChildrenReturned() {
        // arrange
		List<Person> personList = new ArrayList<>(); { personList.add(person1); personList.add(person2); personList.add(person3); }

        // act
		String result = personService.getChildrenInList(personList);

        // assert
		assertEquals(result, "1");
    }

	@Test
	public void getChildrenInList_invalidList_zeroReturned() {
		// arrange
		List<Person> emptyPersonList = new ArrayList<>();

		// act
		String result = personService.getChildrenInList(emptyPersonList);

		// assert
		assertEquals(result, "0");
	}

    @Test
    public void getAdultsInList_validList_numberAdultsReturned() {
		// arrange
		List<Person> personList = new ArrayList<>(); { personList.add(person1); personList.add(person2); personList.add(person3); }

		// act
		String result = personService.getAdultsInList(personList);

		// assert
		assertEquals(result, "2");
	}

	@Test
	public void getAdultsInList_invalidList_zeroReturned() {
		// arrange
		List<Person> emptyPersonList = new ArrayList<>();

		// act
		String result = personService.getAdultsInList(emptyPersonList);

		// assert
		assertEquals(result, "0");
	}

    @Test
    public void getEmailsByCity_cityExists_emailsListReturned() {
        // arrange

        // act
		List<String> result = personService.getEmailsByCity("Giedi Prime");

        // assert
		assertEquals(result.size(), 1);
		assertTrue(result.get(0).equals("duncan@gmail.com"));
    }

	@Test
	public void getEmailsByCity_cityDoesNotExist_emptyListReturned() {
		// arrange

		// act
		List<String> result = personService.getEmailsByCity("Fake City");

		// assert
		assertEquals(result.size(), 0);
	}
}