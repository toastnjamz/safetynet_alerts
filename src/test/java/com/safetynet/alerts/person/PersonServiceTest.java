package com.safetynet.alerts.person;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.MedicalRecordService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

	private PersonRepository personRepository;
	private MedicalRecordService medicalRecordService;
	private MedicalRecordRepository medicalRecordRepository;
	private PersonService personService;
	private Person person1;
	private Person person2;

	@Before
	public void setup() {
		personRepository = new PersonRepository();
		medicalRecordService = new MedicalRecordService(medicalRecordRepository);
		personService = new PersonService(personRepository, medicalRecordService);

		person1 = new Person("Duncan", "Idaho", "123 Market Street", "Giedi Prime",
				"12345", "555-555-5555", "duncan@gmail.com");
		person2 = new Person("Jessica", "Atreides", "456 Grand Palace", "Arrakeen", "12345",
				"555-555-5555", "benegesserit@yahoo.com");

		personService.createPerson(person1);
		personService.createPerson(person2);
	}

	@Test
	public void getAllPersons_repositoryHasData_allDataReturned() {
		// arrange

		// act
        List<Person> result = personService.getAllPersons();

		// assert
        assertTrue(result.get(0).getFirstName().equals("Duncan"));
        assertTrue(result.get(1).getFirstName().equals("Jessica"));
	}

	@Test
	public void getPersonByFirstLastName_repositoryHasData_personDataReturned() {
		// arrange

		// act
        Person result = personService.getPersonByFirstLastName("Duncan", "Idaho");

		// assert
        assertTrue(result.equals(person1));
	}

	@Test
	public void createPerson_addingNewPerson_personDataReturned() {
		// arrange
		Person person3 = new Person("Paul", "Atreides", "123 Grand Palace", "Caladan",
				"12345", "555-555-5555", "maud-dib@gmail.com");

		// act
        Person result = personService.createPerson(person3);;

		// assert
        assertTrue(result.equals(person3));
	}

	@Test
	public void updatePerson_addingAndUpdatingPerson_personDataUpdated() {
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
	public void deletePerson_addingAndDeletingPerson_personDataDeleted() {
		// arrange
		Person person3 = new Person("Paul", "Atreides", "123 Grand Palace", "Caladan",
				"12345", "555-555-5555", "maud-dib@gmail.com");

		Person personCreated = personService.createPerson(person3);
		assertEquals(3, personRepository.findAll().size());

		// act
		personService.deletePerson("Paul", "Atreides");

		// assert
		assertEquals(2, personRepository.findAll().size());
	}

    @Test
    public void getPersonAge_repositoryHasData_personDataReturned() {
        // arrange

        // act

        // assert
    }

    @Test
    public void getPersonInfo_repositoryHasData_personDataReturned() {
        // arrange

        // act

        // assert
    }

    @Test
    public void getPersonsAtAddress_repositoryHasData_personListReturned() {
        // arrange

        // act

        // assert
    }

    @Test
    public void getPersonsAtAddresses_repositoryHasData_personListReturned() {
        // arrange

        // act

        // assert
    }

    @Test
    public void getChildrenAtAddress_repositoryHasData_personListReturned() {
        // arrange

        // act

        // assert
    }

    @Test
    public void getChildrenInList_repositoryHasData_numberChildrenReturned() {
        // arrange

        // act

        // assert
    }

    @Test
    public void getAdultsInList_repositoryHasData_numberAdultsReturned() {
        // arrange

        // act

        // assert
    }

    @Test
    public void getEmailsByCity_repositoryHasData_emailsListReturned() {
        // arrange

        // act

        // assert
    }
}