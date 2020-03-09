package com.safetynet.alerts.MedicalRecord;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void getAllMedicalRecords_repositoryHasData_allDataReturned() throws Exception {
        this.mockMvc.perform(get("/medicalRecords"))
                .andExpect(content().json("[{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1984\",\"medications\":[\"aznol:350mg\",\"hydrapermazol:100mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"Jacob\",\"lastName\":\"Boyd\",\"birthdate\":\"03/06/1989\",\"medications\":[\"pharmacol:5000mg\",\"terazine:10mg\",\"noznazol:250mg\"],\"allergies\":[]},{\"firstName\":\"Tenley\",\"lastName\":\"Boyd\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[\"peanut\"]},{\"firstName\":\"Roger\",\"lastName\":\"Boyd\",\"birthdate\":\"09/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Felicia\",\"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\",\"medications\":[\"tetracyclaz:650mg\"],\"allergies\":[\"xilliathal\"]},{\"firstName\":\"Jonanathan\",\"lastName\":\"Marrack\",\"birthdate\":\"01/03/1989\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Tessa\",\"lastName\":\"Carman\",\"birthdate\":\"02/18/2012\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"birthdate\":\"09/06/2000\",\"medications\":[],\"allergies\":[\"shellfish\"]},{\"firstName\":\"Foster\",\"lastName\":\"Shepard\",\"birthdate\":\"01/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Tony\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":[\"shellfish\"]},{\"firstName\":\"Lily\",\"lastName\":\"Cooper\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1988\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":[\"peanut\",\"shellfish\",\"aznol\"]},{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/1985\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Zach\",\"lastName\":\"Zemicks\",\"birthdate\":\"03/06/2017\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Reginold\",\"lastName\":\"Walker\",\"birthdate\":\"08/30/1979\",\"medications\":[\"thradox:700mg\"],\"allergies\":[\"illisoxian\"]},{\"firstName\":\"Jamie\",\"lastName\":\"Peters\",\"birthdate\":\"03/06/1982\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Ron\",\"lastName\":\"Peters\",\"birthdate\":\"04/06/1965\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Allison\",\"lastName\":\"Boyd\",\"birthdate\":\"03/15/1965\",\"medications\":[\"aznol:200mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"birthdate\":\"12/06/1975\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":[\"nillacilan\"]},{\"firstName\":\"Shawna\",\"lastName\":\"Stelzer\",\"birthdate\":\"07/08/1980\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"birthdate\":\"03/06/2014\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":[]},{\"firstName\":\"Clive\",\"lastName\":\"Ferguson\",\"birthdate\":\"03/06/1994\",\"medications\":[],\"allergies\":[]},{\"firstName\":\"Eric\",\"lastName\":\"Cadigan\",\"birthdate\":\"08/06/1945\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[]}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecordByFirstLastName_medicalRecordExists_medicalRecordDataReturned() throws Exception {
        this.mockMvc.perform(get("/medicalRecord")
                .param("firstName", "John")
                .param("lastName", "Boyd"))
                .andExpect(content().json("{\n" +
                        "    \"firstName\": \"John\",\n" +
                        "    \"lastName\": \"Boyd\",\n" +
                        "    \"birthdate\": \"03/06/1984\",\n" +
                        "    \"medications\": [\n" +
                        "        \"aznol:350mg\",\n" +
                        "        \"hydrapermazol:100mg\"\n" +
                        "    ],\n" +
                        "    \"allergies\": [\n" +
                        "        \"nillacilan\"\n" +
                        "    ]\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecordByFirstLastName_medicalRecordDoesNotExist_isNotFound() throws Exception {
        this.mockMvc.perform(get("/medicalRecord")
                .param("firstName", "Test")
                .param("lastName", "Person"))
                .andExpect(content().string("Medical record for Test Person does not exist"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createMedicalRecord_recordDoesNotAlreadyExist_medicalRecordDataReturned() throws Exception {
        this.mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{ \"firstName\":\"Test\", \"lastName\":\"Person\", \"birthdate\":\"11/26/1984\", \"medications\":[\"testMed1:1mg\", \"testMed2:2mg\"], \"allergies\":[\"fresh air\"] }"))
                .andExpect(content().json("{ \"firstName\":\"Test\", \"lastName\":\"Person\", \"birthdate\":\"11/26/1984\", \"medications\":[\"testMed1:1mg\", \"testMed2:2mg\"], \"allergies\":[\"fresh air\"] }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void createMedicalRecord_recordAlreadyExist_isConflict() throws Exception {
        this.mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"11/26/1984\", \"medications\":[\"testMed1:1mg\", \"testMed2:2mg\"], \"allergies\":[\"fresh air\"] }"))
                .andExpect(content().string("Medical record for John Boyd already exists"))
                .andExpect(status().isConflict());
    }

    @Test
    public void updateMedicalRecord_medicalRecordExists_medicalRecordDataUpdated() throws Exception {
        this.mockMvc.perform(put("/medicalRecord")
                .param("firstName", "John")
                .param("lastName", "Boyd")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n" +
                        "\"firstName\": \"John\",\n" +
                        "\"lastName\": \"Boyd\",\n" +
                        "\"birthdate\": \"03/06/1984\",\n" +
                        "\"medications\": [\n" +
                        "\"aznol:350mg\",\n" +
                        "\"hydrapermazol:100mg\"\n" +
                        "],\n" +
                        "\"allergies\": [\n" +
                        "\"fresh air\"\n" +
                        "]\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecord_medicalRecordDoesNotExist_isNotFound() throws Exception {
        this.mockMvc.perform(put("/medicalRecord")
                .param("firstName", "Test")
                .param("lastName", "Person")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n" +
                        "\"firstName\": \"Test\",\n" +
                        "\"lastName\": \"Person\",\n" +
                        "\"birthdate\": \"03/06/1984\",\n" +
                        "\"medications\": [\n" +
                        "\"aznol:350mg\",\n" +
                        "\"hydrapermazol:1000mg\"\n" +
                        "],\n" +
                        "\"allergies\": [\n" +
                        "\"fresh air\"\n" +
                        "]\n" +
                        "}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteMedicalRecord_medicalRecordExists_medicalRecordDataDeleted() throws Exception {
        this.mockMvc.perform(delete("/medicalRecord")
                .param("firstName", "John")
                .param("lastName", "Boyd"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecord_medicalRecordDoesNotExists_isNotFound() throws Exception {
        this.mockMvc.perform(delete("/medicalRecord")
                .param("firstName", "Test")
                .param("lastName", "Person"))
                .andExpect(status().isNotFound());
    }
}