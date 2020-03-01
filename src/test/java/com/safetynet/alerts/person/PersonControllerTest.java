package com.safetynet.alerts.person;

import com.safetynet.alerts.controller.PersonController;
import com.safetynet.alerts.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(PersonController.class)
//@WebMvcTest only loads controller beans to test handler methods
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext webContext;
//
//    @MockBean
//    private PersonService personService;
//
//    @Before
//    public void setupMockMvc() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
//    }
//
//    @Test
//    public void getAllPerson_personsExist_returnsAllPersons() throws Exception {
//        mockMvc.perform(get("/persons"))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().json("[{\"firstName\":\"John\",\"lastName\":\"Boyd\"}]"));
//    }

    @Test
    public void getAllPerson_personsExist_returnsAllPersons() throws Exception {
        this.mockMvc.perform(get("/persons")).
                andExpect(status().isOk()).
                andExpect(content().json("[{\"firstName\":\"John\",\"lastName\":\"Boyd\"}]"));
    }

    @Test
    public void getPersonByFirstLastName_personExists_returnsPerson() {
        //TODO
    }

    @Test
    public void getPersonByFirstLastName_personDoesNotExist_throwsPersonNotFoundException() {
        //TODO
    }

    @Test
    public void createPerson_personDoesNotAlreadyExist_returnsPerson() {
        //TODO
    }

    @Test
    public void createPerson_personExists_throwsDuplicatePersonException() {
        //TODO
    }

    @Test
    public void updatePerson_personExists_updatesPerson() {
        //TODO
    }

    @Test
    public void updatePerson_personDoesNotExist_throwsPersonNotFoundException() {
        //TODO
    }

    @Test
    public void deletePerson_personExists_deletesPerson() {
        //TODO
    }

    @Test
    public void deletePerson_personDoesNotExist_throwsPersonNotFoundException() {
        //TODO
    }
}
