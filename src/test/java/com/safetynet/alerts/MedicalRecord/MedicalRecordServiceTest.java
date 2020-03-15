package com.safetynet.alerts.MedicalRecord;

import static org.junit.Assert.*;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.MedicalRecordService;
import com.safetynet.alerts.service.MedicalRecordServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class MedicalRecordServiceTest {

    private MedicalRecordRepository medicalRecordRepository;
    private MedicalRecordService medicalRecordService;
    private MedicalRecord medicalRecord1;
    private MedicalRecord medicalRecord2;

    @Before
    public void setup() {
        medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordService = new MedicalRecordServiceImpl(medicalRecordRepository);

        List<String> medicationsList1 = new ArrayList<>(); { medicationsList1.add("medication1"); }
        List<String> medicationsList2 = new ArrayList<>(); { medicationsList2.add("medication2"); }
        List<String> allergiesList1 = new ArrayList<>(); { allergiesList1.add("allergy1"); }
        List<String> allergiesList2 = new ArrayList<>(); { allergiesList2.add("allergy2"); allergiesList2.add("allergy3"); }

        medicalRecord1 = new MedicalRecord("Duncan", "Idaho", "01/01/1960", medicationsList1,
                allergiesList1);
        medicalRecord2 = new MedicalRecord("Jessica", "Atreides", "01/01/1970", medicationsList2,
                allergiesList2);

        medicalRecordService.createMedicalRecord(medicalRecord1);
        medicalRecordService.createMedicalRecord(medicalRecord2);
    }

    @Test
    public void getAllMedicalRecords_repositoryHasData_allMedicalRecordsReturned() {
        // arrange

        // act
        List<MedicalRecord> result = medicalRecordService.getAllMedicalRecords();

        // assert
        assertEquals(result.size(), 2);
    }

    @Test
    public void getMedicalRecordByFirstLastName_medicalRecordExists_medicalRecordDataReturned() {
        // arrange

        // act
        MedicalRecord result = medicalRecordService.getMedicalRecordByFirstLastName("Duncan", "Idaho");

        // assert
        assertTrue(result.getAllergies().size() == 1);
    }

    @Test
    public void getMedicalRecordByFirstLastName_medicalRecordDoesNotExist_nullReturned() {
        // arrange

        // act
        MedicalRecord result = medicalRecordService.getMedicalRecordByFirstLastName("Test", "Person");

        // assert
        assertNull(result);
    }

    @Test
    public void createMedicalRecord_addingValidMedicalRecord_medicalRecordDataReturned() {
        List<String> medicationsList3 = new ArrayList<>(); { medicationsList3.add("medication3"); }
        List<String> allergiesList3 = new ArrayList<>(); { allergiesList3.add("allergy3"); }

        MedicalRecord medicalRecord3 = new MedicalRecord("Paul", "Atreides", "01/01/2010", medicationsList3,
                allergiesList3);

        // act
        MedicalRecord result = medicalRecordService.createMedicalRecord(medicalRecord3);

        // assert
        assertEquals(result.getFirstName(), "Paul");
        assertEquals((result.getMedications().get(0)), "medication3");
    }

    @Test
    public void createMedicalRecord_addingExistingMedicalRecord_nullReturned() {
        // act
        MedicalRecord result = medicalRecordService.createMedicalRecord(medicalRecord1);

        // assert
        assertNull(result);
    }

    @Test
    public void updateMedicalRecord_updatingValidMedicalRecord_medicalRecordDataUpdated() {
        // arrange
        List<String> medicationsList3 = new ArrayList<>(); { medicationsList3.add("medication3"); }
        List<String> allergiesList3 = new ArrayList<>(); { allergiesList3.add("allergy3"); }

        assertEquals(medicalRecord1.getBirthdate(),"01/01/1960");

        // act
        MedicalRecord updatedRecord = new MedicalRecord("Duncan", "Idaho", "02/01/1960", medicationsList3, allergiesList3);

        // assert
        assertEquals(updatedRecord.getBirthdate(), "02/01/1960");
        assertEquals(updatedRecord.getMedications(), medicationsList3);
    }

    @Test
    public void updateMedicalRecord_updatingInvaliddMedicalRecord_nothingHappens() {
        // arrange
        List<String> medicationsList3 = new ArrayList<>(); { medicationsList3.add("medication3"); }
        List<String> allergiesList3 = new ArrayList<>(); { allergiesList3.add("allergy3"); }

        // act
        MedicalRecord updatedRecord = new MedicalRecord("Test", "Person", "02/01/1960", medicationsList3, allergiesList3);

        // assert
        assertNull(medicalRecordService.getMedicalRecordByFirstLastName("Test", "Person"));
    }

    @Test
    public void deleteMedicalRecord_medicalRecordExists_medicalRecordDataDeleted() {
        // arrange
        assertEquals(medicalRecordRepository.findAll().size(), 2);

        // act
        medicalRecordService.deleteMedicalRecord("Duncan", "Idaho");

        // assert
        assertEquals(medicalRecordRepository.findAll().size(), 1);
        assertEquals(medicalRecordRepository.findAll().get(0).getFirstName(), "Jessica");
    }

    @Test
    public void deleteMedicalRecord_medicalRecordDoesNotExist_nothingHappens() {
        // arrange
        assertEquals(medicalRecordRepository.findAll().size(), 2);

        // act
        medicalRecordService.deleteMedicalRecord("Test", "Person");

        // assert
        assertEquals(medicalRecordRepository.findAll().size(), 2);
    }
}