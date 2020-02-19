package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
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

    public List<FireStation> getAllFireStations() {
        return fireStationRepository.findAll();
    }

    public FireStation getFireStationByAddress(String address) throws NotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new NotFoundException();
        }
        return fireStationRepository.findStation(address);
    }

    public FireStation createFireStation(FireStation fireStation) throws DuplicateException {
        for (FireStation stationInList : fireStationRepository.findAll()) {
            if (stationInList.equals(fireStation)) {
                throw new DuplicateException();
            }
        }
        return fireStationRepository.createStation(fireStation);
    }

    public void updateFireStation(FireStation fireStation) throws NotFoundException {
        if (fireStationRepository.findStation(fireStation.getAddress()) == null) {
            throw new NotFoundException();
        }
        fireStationRepository.updateStation(fireStation);
    }

    public void deleteFireStation(String address) throws NotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new NotFoundException();
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

    public List<Person> getPersonsByStationNumber(String stationNumber) {
        List<Person> personList = new ArrayList<>();

        if (fireStationRepository.findAll().stream().anyMatch(f -> f.getStationNo().equals(stationNumber))) {
            List<String> addressList = getAddressesByStationNumber(stationNumber);
            personList = personService.getPersonsAtAddresses(addressList);
        }
        return personList;
    }

    public List<String> getPhoneNumbersByStationNumber(String stationNumber) {
        List<String> phoneNumbers = new ArrayList<>();

        if (fireStationRepository.findAll().stream().anyMatch(f -> f.getStationNo().equals(stationNumber))) {
            List<Person> personList = getPersonsByStationNumber(stationNumber);
            for (Person personInList : personList) {
                phoneNumbers.add(personInList.getPhone());
            }
        }
        return phoneNumbers;
    }
}
