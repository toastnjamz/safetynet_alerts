package com.safetynet.alerts.FireStation;

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
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void getAllFireStations_repositoryHasData_allDataReturned() throws Exception {
        this.mockMvc.perform(get("/firestations"))
                .andExpect(content().json("[{\"address\":\"1509 Culver St\",\"station\":\"3\"},{\"address\":\"29 15th St\",\"station\":\"2\"},{\"address\":\"834 Binoc Ave\",\"station\":\"3\"},{\"address\":\"644 Gershwin Cir\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"112 Steppes Pl\",\"station\":\"3\"},{\"address\":\"489 Manchester St\",\"station\":\"4\"},{\"address\":\"892 Downing Ct\",\"station\":\"2\"},{\"address\":\"908 73rd St\",\"station\":\"1\"},{\"address\":\"112 Steppes Pl\",\"station\":\"4\"},{\"address\":\"947 E. Rose Dr\",\"station\":\"1\"},{\"address\":\"748 Townings Dr\",\"station\":\"3\"},{\"address\":\"951 LoneTree Rd\",\"station\":\"2\"}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getFireStationByAddress_fireStationExists_fireStationDataReturned() throws Exception {
        this.mockMvc.perform(get("/Firestation")
                .param("address", "1509 Culver St"))
                .andExpect(content().json("{\"address\":\"1509 Culver St\",\"station\":\"3\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void createFireStation_addingValidFireStation_fireStationDataReturned() throws Exception {
        this.mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"address\":\"123 Easy Street\",\"station\":\"1\"}"))
                .andExpect(content().json("{\"address\":\"123 Easy Street\",\"station\":\"1\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateFireStation_updatingValidFireStation_fireStationDataUpdated() throws Exception {
        this.mockMvc.perform(put("/firestation")
                .param("address", "1509 Culver St")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n" +
                        "\"address\": \"1509 Culver St\",\n" +
                        "\"station\": \"10\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFireStation_fireStationExists_fireStationDataDeleted() throws Exception {
        this.mockMvc.perform(delete("/firestation")
                .param("address", "1509 Culver St"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonsByStation_fireStationExists_personDataReturned() throws Exception {
        this.mockMvc.perform(get("/firestation")
                .param("stationNumber", "1"))
                .andExpect(content().json("{\"formattedPersonList\":[\"Peter\",\"Duncan\",\"644 Gershwin Cir\",\"841-874-6512\",\"Reginold\",\"Walker\",\"908 73rd St\",\"841-874-8547\",\"Jamie\",\"Peters\",\"908 73rd St\",\"841-874-7462\",\"Brian\",\"Stelzer\",\"947 E. Rose Dr\",\"841-874-7784\",\"Shawna\",\"Stelzer\",\"947 E. Rose Dr\",\"841-874-7784\",\"Kendrik\",\"Stelzer\",\"947 E. Rose Dr\",\"841-874-7784\"],\"numberOfAdults\":\"5\",\"numberOfChildren\":\"1\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getStationAndPersonsByAddress_addressExists_stationAndPersonDataReturned() throws Exception {
        this.mockMvc.perform(get("/fire")
                .param("address", "908 73rd St"))
                .andExpect(content().json("{\"1\":[{\"firstName\":\"Peter\",\"lastName\":\"Duncan\",\"medications\":[],\"allergies\":[\"shellfish\"],\"phone\":\"841-874-6512\",\"age\":\"19\"},{\"firstName\":\"Reginold\",\"lastName\":\"Walker\",\"medications\":[\"thradox:700mg\"],\"allergies\":[\"illisoxian\"],\"phone\":\"841-874-8547\",\"age\":\"40\"},{\"firstName\":\"Jamie\",\"lastName\":\"Peters\",\"medications\":[],\"allergies\":[],\"phone\":\"841-874-7462\",\"age\":\"38\"},{\"firstName\":\"Brian\",\"lastName\":\"Stelzer\",\"medications\":[\"ibupurin:200mg\",\"hydrapermazol:400mg\"],\"allergies\":[\"nillacilan\"],\"phone\":\"841-874-7784\",\"age\":\"44\"},{\"firstName\":\"Shawna\",\"lastName\":\"Stelzer\",\"medications\":[],\"allergies\":[],\"phone\":\"841-874-7784\",\"age\":\"39\"},{\"firstName\":\"Kendrik\",\"lastName\":\"Stelzer\",\"medications\":[\"noxidian:100mg\",\"pharmacol:2500mg\"],\"allergies\":[],\"phone\":\"841-874-7784\",\"age\":\"6\"}]}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getHouseholdsByStationList_stationListValid_householdDataReturned() throws Exception {
        this.mockMvc.perform(get("/flood/stations")
                .param("stations", "2,4"))
                .andExpect(content().json("[{\"address\":\"29 15th St\",\"formattedPersonList\":[{\"firstName\":\"Jonanathan\",\"lastName\":\"Marrack\",\"medications\":[],\"allergies\":[],\"phone\":\"841-874-6513\",\"age\":\"31\"}]},{\"address\":\"892 Downing Ct\",\"formattedPersonList\":[{\"firstName\":\"Sophia\",\"lastName\":\"Zemicks\",\"medications\":[\"aznol:60mg\",\"hydrapermazol:900mg\",\"pharmacol:5000mg\",\"terazine:500mg\"],\"allergies\":[\"peanut\",\"shellfish\",\"aznol\"],\"phone\":\"841-874-7878\",\"age\":\"32\"},{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\",\"medications\":[],\"allergies\":[],\"phone\":\"841-874-7512\",\"age\":\"35\"},{\"firstName\":\"Zach\",\"lastName\":\"Zemicks\",\"medications\":[],\"allergies\":[],\"phone\":\"841-874-7512\",\"age\":\"3\"}]},{\"address\":\"951 LoneTree Rd\",\"formattedPersonList\":[{\"firstName\":\"Eric\",\"lastName\":\"Cadigan\",\"medications\":[\"tradoxidine:400mg\"],\"allergies\":[],\"phone\":\"841-874-7458\",\"age\":\"74\"}]},{\"address\":\"489 Manchester St\",\"formattedPersonList\":[{\"firstName\":\"Lily\",\"lastName\":\"Cooper\",\"medications\":[],\"allergies\":[],\"phone\":\"841-874-9845\",\"age\":\"26\"}]},{\"address\":\"112 Steppes Pl\",\"formattedPersonList\":[{\"firstName\":\"Tony\",\"lastName\":\"Cooper\",\"medications\":[\"hydrapermazol:300mg\",\"dodoxadin:30mg\"],\"allergies\":[\"shellfish\"],\"phone\":\"841-874-6874\",\"age\":\"26\"},{\"firstName\":\"Ron\",\"lastName\":\"Peters\",\"medications\":[],\"allergies\":[],\"phone\":\"841-874-8888\",\"age\":\"54\"},{\"firstName\":\"Allison\",\"lastName\":\"Boyd\",\"medications\":[\"aznol:200mg\"],\"allergies\":[\"nillacilan\"],\"phone\":\"841-874-9888\",\"age\":\"54\"}]}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPhoneNumbersByStation_fireStationExists_phoneDataReturned() throws Exception {
        this.mockMvc.perform(get("/phoneAlert")
                .param("firestation", "1"))
                .andExpect(content().json("[\"841-874-6512\",\"841-874-8547\",\"841-874-7462\",\"841-874-7784\",\"841-874-7784\",\"841-874-7784\"]"))
                .andExpect(status().isOk());
    }
}