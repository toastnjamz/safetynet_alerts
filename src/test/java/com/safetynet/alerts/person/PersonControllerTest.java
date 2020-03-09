package com.safetynet.alerts.person;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void getAllPersons_repositoryHasData_allDataReturned() throws Exception {
        this.mockMvc.perform(get("/persons"))
                .andExpect(content().json("[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6513\",\"email\":\"drk@email.com\"},{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"tenz@email.com\"},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6544\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Jonanathan\",\"lastName\":\"Marrack\",\"address\":\"29 15th St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6513\",\"email\":\"drk@email.com\"},{\"firstName\":\"Tessa\",\"lastName\":\"Carman\",\"address\":\"834 Binoc Ave\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"tenz@email.com\"},{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"address\":\"644 Gershwin Cir\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Foster\",\"lastName\":\"Shepard\",\"address\":\"748 Townings Dr\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6544\",\"email\":\"jaboyd@email.com\"},{\"firstName\":\"Tony\",\"lastName\":\"Cooper\",\"address\":\"112 Steppes Pl\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6874\",\"email\":\"tcoop@ymail.com\"},{\"firstName\":\"Lily\",\"lastName\":\"Cooper\",\"address\":\"489 Manchester St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-9845\",\"email\":\"lily@email.com\"},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7878\",\"email\":\"soph@email.com\"},{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7512\",\"email\":\"ward@email.com\"},{\"firstName\":\"Zach\",\"lastName\":\"Zemicks\",\"address\":\"892 Downing Ct\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7512\",\"email\":\"zarc@email.com\"},{\"firstName\":\"Reginold\",\"lastName\":\"Walker\",\"address\":\"908 73rd St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-8547\",\"email\":\"reg@email.com\"},{\"firstName\":\"Jamie\",\"lastName\":\"Peters\",\"address\":\"908 73rd St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7462\",\"email\":\"jpeter@email.com\"},{\"firstName\":\"Ron\",\"lastName\":\"Peters\",\"address\":\"112 Steppes Pl\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-8888\",\"email\":\"jpeter@email.com\"},{\"firstName\":\"Allison\",\"lastName\":\"Boyd\",\"address\":\"112 Steppes Pl\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-9888\",\"email\":\"aly@imail.com\"},{\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7784\",\"email\":\"bstel@email.com\"},{\"firstName\":\"Shawna\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7784\",\"email\":\"ssanw@email.com\"},{\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"address\":\"947 E. Rose Dr\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7784\",\"email\":\"bstel@email.com\"},{\"firstName\":\"Clive\",\"lastName\":\"Ferguson\",\"address\":\"748 Townings Dr\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6741\",\"email\":\"clivfd@ymail.com\"},{\"firstName\":\"Eric\",\"lastName\":\"Cadigan\",\"address\":\"951 LoneTree Rd\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-7458\",\"email\":\"gramps@email.com\"}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonByFirstLastName_personExists_personDataReturned() throws Exception {
        this.mockMvc.perform(get("/person")
                .param("firstName", "John")
                .param("lastName", "Boyd"))
                .andExpect(content().json("{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonByFirstLastName_personDoesNotExist_isNotFound() throws Exception {
        this.mockMvc.perform(get("/person")
                .param("firstName", "Test")
                .param("lastName", "Person"))
                .andExpect(content().string("Person with name Test Person does not exist"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createPerson_personDoesNotAlreadyExist_personDataReturned() throws Exception {
        this.mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"firstName\":\"Test\",\"lastName\":\"Person\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                .andExpect(content().json("{\"firstName\":\"Test\",\"lastName\":\"Person\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void createPerson_personAlreadyExist_isConflict() throws Exception {
        this.mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"1509 Culver St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                .andExpect(content().string("Person John Boyd already exists"))
                .andExpect(status().isConflict());
    }

    @Test
    public void updatePerson_personExists_personDataUpdated() throws Exception {
        this.mockMvc.perform(put("/person")
                .param("firstName", "John")
                .param("lastName", "Boyd")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"123 Test St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePerson_personDoesNotExist_isNotFound() throws Exception {
        this.mockMvc.perform(put("/person")
                .param("firstName", "Test")
                .param("lastName", "Person")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"firstName\":\"Test\",\"lastName\":\"Person\",\"address\":\"123 Test St\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"841-874-6512\",\"email\":\"jaboyd@email.com\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePerson_personExists_personDataDeleted() throws Exception {
        this.mockMvc.perform(delete("/person")
                .param("firstName", "John")
                .param("lastName", "Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePerson_personDoesNotExist_isNotFound() throws Exception {
        this.mockMvc.perform(delete("/person")
                .param("firstName", "Test")
                .param("lastName", "Person"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPersonInfo_personExists_personInfoReturned() throws Exception {
        this.mockMvc.perform(get("/personInfo")
                .param("firstName", "John")
                .param("lastName", "Boyd"))
                .andExpect(content().json("{\"address\":\"1509 Culver St\",\"firstName\":\"John\",\"lastName\":\"Boyd\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"],\"email\":\"jaboyd@email.com\",\"age\":\"36\"}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonInfo_personDoesNotExist_isNotFound() throws Exception {
        this.mockMvc.perform(get("/personInfo")
                .param("firstName", "Test")
                .param("lastName", "Person"))
                .andExpect(content().string("Person with name Test Person does not exist"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getChildrenAtAddress_validAddress_personListReturned() throws Exception {
        this.mockMvc.perform(get("/childAlert")
                .param("address", "1509 Culver St"))
                .andExpect(content().json("[{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"age\":\"8\"},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"age\":\"2\"},{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"age\":\"36\"},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"age\":\"31\"},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"age\":\"34\"}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getChildrenAtAddress_invalidAddress__emptyStringReturned() throws Exception {
        this.mockMvc.perform(get("/childAlert")
                .param("address", "1 Imaginary Street"))
                .andExpect(content().string(""))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPersonsEmailsByCity_cityExists_emailsListReturned() throws Exception {
        this.mockMvc.perform(get("/communityEmail")
                .param("city", "Culver"))
                .andExpect(content().json("[\n" +
                        "    \"jaboyd@email.com\",\n" +
                        "    \"drk@email.com\",\n" +
                        "    \"tenz@email.com\",\n" +
                        "    \"jaboyd@email.com\",\n" +
                        "    \"jaboyd@email.com\",\n" +
                        "    \"drk@email.com\",\n" +
                        "    \"tenz@email.com\",\n" +
                        "    \"jaboyd@email.com\",\n" +
                        "    \"jaboyd@email.com\",\n" +
                        "    \"tcoop@ymail.com\",\n" +
                        "    \"lily@email.com\",\n" +
                        "    \"soph@email.com\",\n" +
                        "    \"ward@email.com\",\n" +
                        "    \"zarc@email.com\",\n" +
                        "    \"reg@email.com\",\n" +
                        "    \"jpeter@email.com\",\n" +
                        "    \"jpeter@email.com\",\n" +
                        "    \"aly@imail.com\",\n" +
                        "    \"bstel@email.com\",\n" +
                        "    \"ssanw@email.com\",\n" +
                        "    \"bstel@email.com\",\n" +
                        "    \"clivfd@ymail.com\",\n" +
                        "    \"gramps@email.com\"\n" +
                        "]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonsEmailsByCity_cityDoesNotExist_isNotFound() throws Exception {
        this.mockMvc.perform(get("/communityEmail")
                .param("city", "Universal City"))
                .andExpect(content().string("No emails were found for Universal City"))
                .andExpect(status().isNotFound());
    }
}
