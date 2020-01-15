package com.safetynet.alerts.person;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
		//List<Person> result = personService.getAllPersons();
		String result = personService.getAllPersons();
		
		// assert
//		Assert.assertTrue(result.size() == 2);
//		Assert.assertTrue(result.get(0).getFirstName().equals("Duncan"));
//		Assert.assertTrue(result.get(1).getFirstName().equals("Jessica"));
		
		//JSONAssert.assertEquals()
		
	}
	
	@Test
	public void getPersonByFirstLastName_repositoryHasJsonData_personDataReturned() {
		// arrange
		Person person1 = new Person("Duncan", "Idaho", "123 Harkonnen Street", "Giedi Prime",
				"94501", "555-555-5555", "duncan@gmail.com");
		
		when(personRepositoryMock.findPerson("Duncan", "Idaho")).thenReturn(person1);
		
		// act
		String foundPerson = personService.getPersonByFirstLastName(person1.getFirstName(), person1.getLastName());
		
		// assert
		assertEquals("Duncan", foundPerson.getFirstName());
		
	}
	
	@Test
	public void createPerson_addingOnePerson_personDataReturned() {
		// arrange
		
		
		// act
		
		
		// assert
		
	}
	
	@Test
	public void updatePerson_addingAndUpdatingPerson_personDataUpdated() {
		// arrange
		
		
		// act
		
		
		// assert
		
	}
	
	@Test
	public void deletePerson_addingAndDeletingPerson_personDataDeleted() {
		// arrange
		
		
		// act
		
		
		// assert
		
	}
}
