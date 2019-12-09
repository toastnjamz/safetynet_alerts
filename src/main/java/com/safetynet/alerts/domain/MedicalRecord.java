package com.safetynet.alerts.domain;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<Medication> medications;
	private ArrayList<Allergy> allergies;
	
	public MedicalRecord(String firstName, String lastName, String birthdate, 
			List<Medication> medications, ArrayList<Allergy> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	public ArrayList<Allergy> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<Allergy> allergies) {
		this.allergies = allergies;
	}
}
