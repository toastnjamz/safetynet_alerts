package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MedicalRecordService {

    public List<MedicalRecord> getAllMedicalRecords();

    public MedicalRecord getMedicalRecordByFirstLastName(String firstName, String lastName);

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

    public void updateMedicalRecord(MedicalRecord medicalRecord);

    public void deleteMedicalRecord(String firstName, String lastName);
}
