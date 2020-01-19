package com.safetynet.alerts.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	
	@InjectMocks
	private PersonService personService;
	
	@Mock
	private PersonRepository personRepositoryMock;
	
	//TODO: Create @Before setup method?
	
	@Test
	public void getAllPersons_repositoryHasData_allDataReturned() {
		// arrange
		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
				"94501", "555-555-5555", "duncan@gmail.com");
		Person person2 = new Person("Jessica", "Atreides", "456 Grand Palace", "Arrakeen", "94501",
				"555-555-5555", "benegesserit@yahoo.com");
		List<Person> mockPersons = Arrays.asList(person1, person2);
		
		when(personRepositoryMock.findAll()).thenReturn(mockPersons);
		
		// act
		String result = personService.getAllPersons();
		
		// assert
		assertThat(result, CoreMatchers.containsString("Duncan"));
		assertThat(result, CoreMatchers.containsString("Jessica"));
	}
	
	@Test
	public void getPersonByFirstLastName_repositoryHasData_personDataReturned() {
		// arrange
		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
				"94501", "555-555-5555", "duncan@gmail.com");
		
		when(personRepositoryMock.findPerson("Duncan", "Idaho")).thenReturn(person1);
		
		// act
		String foundPerson = personService.getPersonByFirstLastName(person1.getFirstName(), person1.getLastName());
		
		// assert
		assertThat(foundPerson, CoreMatchers.containsString("Duncan"));
	}
	
	@Test
	public void createPerson_addingOnePerson_personDataReturned() {
		// arrange
		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
				"94501", "555-555-5555", "duncan@gmail.com");
		
		when(personRepositoryMock.createPerson(person1)).thenReturn(person1);
		
		// act
		String personCreated = personService.createPerson(person1);
		
		// assert
		assertThat(personCreated, CoreMatchers.containsString("Duncan"));
	}
	
	@Test
	public void updatePerson_addingAndUpdatingPerson_personDataUpdated() {
		// arrange
		
		
		// act
		
		
		// assert
		
	}
	
	// THIS IS THE PROBLEM TEST (the above tests pass).
	@Test
	public void deletePerson_addingAndDeletingPerson_personDataDeleted() {
		// arrange
		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
				"94501", "555-555-5555", "duncan@gmail.com");
		
		when(personRepositoryMock.createPerson(person1)).thenReturn(person1);
		
		// act
		String personCreated = personService.createPerson(person1);
		
		// assert
		assertThat(personCreated, CoreMatchers.containsString("Duncan"));
		assertEquals(false, personRepositoryMock.findAll().isEmpty());
	}
	
//	@Test
//	public void deletePerson_addingAndDeletingPerson_personDataDeleted() {
//		// arrange
//		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
//				"94501", "555-555-5555", "duncan@gmail.com");
//		
//		ArgumentCaptor<String> firstNameCaptor = ArgumentCaptor.forClass(String.class);
//		ArgumentCaptor<String> lastNameCaptor = ArgumentCaptor.forClass(String.class);
//		
//		when(personRepositoryMock.createPerson(person1)).thenReturn(person1);
//		personService.createPerson(person1);
//		
//		// act
//		assertEquals(false, personRepositoryMock.findAll().isEmpty());
//		personService.deletePerson(person1.getFirstName(), person1.getLastName());
//		assertEquals(true, personRepositoryMock.findAll().isEmpty());
//		
//		// assert
//		verify(personRepositoryMock, times(1)).deletePerson(firstNameCaptor.capture(), lastNameCaptor.capture());
//		assertEquals("Duncan", firstNameCaptor.getValue());
//		assertEquals("Idaho", lastNameCaptor.getValue());
//	}
	
//	@Test
//	public void deletePerson_addingAndDeletingPerson_personDataDeleted() {
//		// arrange
//		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
//				"94501", "555-555-5555", "duncan@gmail.com");
//		
//		ArgumentCaptor<String> firstNameCaptor = ArgumentCaptor.forClass(String.class);
//		ArgumentCaptor<String> lastNameCaptor = ArgumentCaptor.forClass(String.class);
//		
//		when(personRepositoryMock.createPerson(person1)).thenReturn(person1);
//		personService.createPerson(person1);
//		
//		doNothing().when(personRepositoryMock).deletePerson(firstNameCaptor.capture(), lastNameCaptor.capture());
//		
//		// act
//		personService.deletePerson(person1.getFirstName(), person1.getLastName());
//		
//		// assert
//		verify(personRepositoryMock, times(1)).deletePerson(firstNameCaptor.capture(), lastNameCaptor.capture());
//		assertEquals("Duncan", firstNameCaptor.getValue());
//		assertEquals("Idaho", lastNameCaptor.getValue());
//	}
}
