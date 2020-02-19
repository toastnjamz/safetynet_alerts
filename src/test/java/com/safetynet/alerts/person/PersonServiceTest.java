//package com.safetynet.alerts.person;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
//
//import com.safetynet.alerts.domain.MedicalRecord;
//import com.safetynet.alerts.exception.DuplicatePersonException;
//import com.safetynet.alerts.exception.PersonNotFoundException;
//import com.safetynet.alerts.repository.MedicalRecordRepository;
//import org.hamcrest.CoreMatchers;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.safetynet.alerts.domain.Person;
//import com.safetynet.alerts.repository.PersonRepository;
//import com.safetynet.alerts.service.PersonService;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//public class PersonServiceTest {
//
//	private PersonRepository personRepository;
//	private MedicalRecordRepository medicalRecordRepository;
//	private PersonService personService;
//
//	@Before
//	public void setup() {
//		personRepository = new PersonRepository();
//		medicalRecordRepository = new MedicalRecordRepository();
//		personService = new PersonService(personRepository, medicalRecordRepository);
//
//		Person person1 = new Person("Duncan", "Idaho", "123 Market Street", "Giedi Prime",
//				"12345", "555-555-5555", "duncan@gmail.com");
//		Person person2 = new Person("Jessica", "Atreides", "456 Grand Palace", "Arrakeen", "12345",
//				"555-555-5555", "benegesserit@yahoo.com");
//
//		personService.createPerson(person1);
//		personService.createPerson(person2);
//	}
//
//	@Test
//	public void getAllPersons_repositoryHasData_allDataReturned() {
//		// arrange
//
//		// act
//		String result = personService.getAllPersons();
//
//		// assert
//		assertThat(result, CoreMatchers.containsString("Duncan"));
//		assertThat(result, CoreMatchers.containsString("Jessica"));
//		//assertEquals(2, personRepository.findAll().size());
//	}
//
//	@Test
//	public void getPersonByFirstLastName_repositoryHasData_personDataReturned() {
//		// arrange
//
//		// act
//		String foundPerson = personService.getPersonByFirstLastName("Duncan", "Idaho");
//
//		// assert
//		assertThat(foundPerson, CoreMatchers.containsString("Giedi Prime"));
//	}
//
//	@Test (expected = PersonNotFoundException.class)
//	public void getPersonByFirstLastName_personFirstLastNameNull_throwsPersonNotFoundException() {
//		// arrange
//
//		// act
//		String foundPerson = personService.getPersonByFirstLastName("Duncan", "Atreides");
//
//		// assert
//	}
//
//	@Test
//	public void createPerson_addingNewPerson_personDataReturned() {
//		// arrange
//		Person person3 = new Person("Paul", "Atreides", "123 Grand Palace", "Caladan",
//				"12345", "555-555-5555", "maud-dib@gmail.com");
//
//		// act
//		String personCreated = personService.createPerson(person3);
//
//		// assert
//		assertThat(personCreated, CoreMatchers.containsString("Paul"));
//		//assertEquals(3, personRepository.findAll().size());
//	}
//
//	@Test (expected = DuplicatePersonException.class)
//	public void createPerson_addingExistingPerson_throwsDuplicatePersonException() {
//		// arrange
//		Person person3 = new Person("Duncan", "Idaho", "123 Market Street", "Giedi Prime",
//				"12345", "555-555-5555", "duncan@gmail.com");
//
//		// act
//		String personCreated = personService.createPerson(person3);
//
//		// assert
//	}
//
//	@Test
//	public void updatePerson_addingAndUpdatingPerson_personDataUpdated() {
//		// arrange
//		Person updatedPerson1 = new Person("Duncan", "Idaho", "456 Sietch Tabr", "Arrakis",
//				"67899", "666-666-6666", "shaihulud@gmail.com");
//
//		// act
//		personService.updatePerson(updatedPerson1);
//		String personUpdated = personService.getPersonByFirstLastName("Duncan", "Idaho");
//
//		// assert
//		assertThat(personUpdated, CoreMatchers.containsString("456 Sietch Tabr"));
//	}
//
//	@Test (expected = PersonNotFoundException.class)
//	public void updatePerson_updatingNewPerson_throwsPersonNotFoundException() {
//		// arrange
//		Person person3 = new Person("Paul", "Atreides", "123 Grand Palace", "Caladan",
//				"12345", "555-555-5555", "maud-dib@gmail.com");
//
//		// act
//		personService.updatePerson(person3);
//
//		// assert
//	}
//
//	@Test
//	public void deletePerson_addingAndDeletingPerson_personDataDeleted() {
//		// arrange
//		Person person3 = new Person("Paul", "Atreides", "123 Grand Palace", "Caladan",
//				"12345", "555-555-5555", "maud-dib@gmail.com");
//
//		String personCreated = personService.createPerson(person3);
//		assertThat(personCreated, CoreMatchers.containsString("Paul"));
//		//assertEquals(3, personRepository.findAll().size());
//
//		// act
//		personService.deletePerson("Paul", "Atreides");
//
//		// assert
//		assertEquals(2, personRepository.findAll().size());
//	}
//
//	@Test (expected = PersonNotFoundException.class)
//	public void deletePerson_deletingNewPerson_throwsPersonNotFoundException() {
//		// arrange
//
//		// act
//		personService.deletePerson("Paul", "Atreides");
//
//		// assert
//	}
//}
