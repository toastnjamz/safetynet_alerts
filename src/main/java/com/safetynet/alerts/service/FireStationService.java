package com.safetynet.alerts.service;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicateFireStationException;
import com.safetynet.alerts.exception.FireStationNotFoundException;
import com.safetynet.alerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private FireStationRepository fireStationRepository;
    private PersonService personService;

    @Autowired
    public FireStationService(FireStationRepository fireStationRepository, PersonService personService) {
        this.fireStationRepository = fireStationRepository;
        this.personService = personService;
    }

    public String getAllFireStations() {
        return JsonStream.serialize(fireStationRepository.findAll());
    }

    public String getFireStationByAddress(String address) throws FireStationNotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new FireStationNotFoundException();
        }
        return JsonStream.serialize(fireStationRepository.findStation(address));
    }

    public String createFireStation(FireStation fireStation) throws DuplicateFireStationException {
        for (FireStation stationInList : fireStationRepository.findAll()) {
            if (stationInList.equals(fireStation)) {
                throw new DuplicateFireStationException();
            }
        }
        return JsonStream.serialize(fireStationRepository.createStation(fireStation));
    }

    public void updateFireStation(FireStation fireStation) throws FireStationNotFoundException {
        if (fireStationRepository.findStation(fireStation.getAddress()) == null) {
            throw new FireStationNotFoundException();
        }
        fireStationRepository.updateStation(fireStation);
    }

    public void deleteFireStation(String address) throws FireStationNotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new FireStationNotFoundException();
        }
        fireStationRepository.deleteStation(address);
    }

    //TODO
    public List<String> getAddressesByStationNumber(String stationNumber) {
        List<String> addressList = new ArrayList<>();
        List<FireStation> stationList = fireStationRepository.findAll().stream().filter(f -> f.getStationNo().equals(stationNumber)).collect(Collectors.toList());

        for (FireStation fireStation : stationList) {
//            if (!addressList.contains(fireStation.getAddress())) {
//                addressList.add(fireStation.getAddress());
//            }
            addressList.add(fireStation.getAddress());
        }
        return addressList;
    }

    //TODO
    public List<Person> getPersonsByStationNumber(String stationNumber) {
        List<Person> personList = new ArrayList<>();

        if (fireStationRepository.findAll().stream().anyMatch(f -> f.getStationNo().equals(stationNumber))) {
            List<String> addressList = getAddressesByStationNumber(stationNumber);
            personList = personService.getPersonsAtAddresses(addressList);
        }
        return personList;
    }

    //TODO
    public String getPhoneNumbersByStationNumber(String stationNumber) {
        List<String> phoneNumbers = new ArrayList<>();

        if (fireStationRepository.findAll().stream().anyMatch(f -> f.getStationNo().equals(stationNumber))) {
            List<Person> personList = getPersonsByStationNumber(stationNumber);
            for (Person personInList : personList) {
                phoneNumbers.add(personInList.getPhone());
            }
        }
        return JsonStream.serialize(phoneNumbers);
    }
}
