package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.domain.AdultsAndChildrenServicedByStation;
import com.safetynet.alerts.domain.Household;
import com.safetynet.alerts.domain.Person;

import java.util.HashMap;
import java.util.List;

public interface FireStationService {

    public List<FireStation> getAllFireStations();

    public FireStation getFireStationByAddress(String address);

    public FireStation createFireStation(FireStation fireStation);

    public void updateFireStation(FireStation fireStation);

    public void deleteFireStation(String address);

    public List<String> getAddressesByStationNumber(String stationNumber);

    public List<Person> getPersonsByStationNumber(String stationNumber);

    public AdultsAndChildrenServicedByStation getListAdultsAndChildrenByStationNumber(String stationNumber);

    public HashMap<String, List<Person>> getStationAndPersonsByAddress(String address);

    public List<Household> getHouseholdsByStationList(List<String> stationNumberList);

    public List<String> getPhoneNumbersByStationNumber(String stationNumber);
}
