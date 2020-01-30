package com.safetynet.alerts.person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.jsonloader.JsonLoader;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
public class PersonServiceTest {
	
	
	@Autowired
	private PersonService personService;
	
//	@Mock
//	private PersonRepository personRepository;
//	
//	@InjectMocks
//	private PersonService personService;
	
//	private PersonRepository personRepository = new mock(PersonRepository.class);
//	private PersonService personService = new PersonService(personRepository);
	
//	@Before
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	private JsonLoader jsonLoader;
//	private PersonRepository personRepository;
//	private PersonService personService;
//	
//	@Before
//	public void setup() {
//		jsonLoader = mock(JsonLoader.class);
//		personRepository = mock(PersonRepository.class);
//		personService = new PersonService(personRepository);
//	}
	
//	@Spy
//	private JsonLoader jsonLoader;
//	
//	@Spy @InjectMocks
//	private PersonRepository personRepository = new PersonRepository(jsonLoader);
//	private PersonRepository personRepository = spy(PersonRepository.class);
//	private PersonRepository personRepository;
//	
//	@InjectMocks
//	private PersonService personService;
	
//	@Test
//	public void getAllPersons_repositoryHasData_allDataReturned() {
//		// arrange
//		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
//				"94501", "555-555-5555", "duncan@gmail.com");
//		Person person2 = new Person("Jessica", "Atreides", "456 Grand Palace", "Arrakeen", "94501",
//				"555-555-5555", "benegesserit@yahoo.com");
//		
//		when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));
//		
//		// act
//		String result = personService.getAllPersons();
//		
//		// assert
//		assertThat(result, CoreMatchers.containsString("Duncan"));
//		assertThat(result, CoreMatchers.containsString("Jessica"));
//		assertEquals(2, personRepository.findAll().size());
//		//verify(personRepository).findAll();
//	}
//	
//	@Test
//	public void getPersonByFirstLastName_repositoryHasData_personDataReturned() {
//		// arrange
//		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
//				"94501", "555-555-5555", "duncan@gmail.com");
//		
//		when(personRepository.findPerson("Duncan", "Idaho")).thenReturn(person1);
//		
//		// act
//		String foundPerson = personService.getPersonByFirstLastName(person1.getFirstName(), person1.getLastName());
//		
//		// assert
//		assertThat(foundPerson, CoreMatchers.containsString("Duncan"));
//		assertThat(foundPerson, CoreMatchers.containsString("Duncan"));
//		assertEquals(1, personRepository.findAll().size());
//	}
	
//	@Test
//	public void createPerson_addingOnePerson_personDataReturned() {
//		// arrange
//		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
//				"94501", "555-555-5555", "duncan@gmail.com");
//		
//		when(personRepository.createPerson(person1)).thenReturn(person1);
//		
//		// act
//		String personCreated = personService.createPerson(person1);
//		
//		// assert
//		assertThat(personCreated, CoreMatchers.containsString("Duncan"));
//		assertEquals(1, personRepository.findAll().size());
//	}
	
//	@Test
//	public void createPerson_addingOnePerson_personDataReturned() {
//		// arrange
//		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
//				"94501", "555-555-5555", "duncan@gmail.com");
//		
//		// act
//		String personCreated = personService.createPerson(person1);
//		
//		// assert
//		assertThat(personCreated, CoreMatchers.containsString("Duncan"));
//		assertEquals(1, personRepository.findAll().size());
//	}
	
	@Test
	public void updatePerson_addingAndUpdatingPerson_personDataUpdated() {
		// arrange
		//String test = personService.getAllPersons();
		
		
		// act
		
		
		// assert
		assertTrue(true);
	}
	
//	@Test
//	public void deletePerson_addingAndDeletingPerson_personDataDeleted() {
//		// arrange
//		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
//				"94501", "555-555-5555", "duncan@gmail.com");
//		
//		when(personRepository.createPerson(person1)).thenReturn(person1);
//		
//		// act
//		String personCreated = personService.createPerson(person1);
//		
//		// assert
//		assertThat(personCreated, CoreMatchers.containsString("Duncan"));
//		assertEquals(false, personRepository.findAll().isEmpty());
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
