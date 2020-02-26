package com.safetynet.alerts.controller;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@RestController
public class FireStationController {

    private static final Logger log = LoggerFactory.getLogger(FireStationController.class);

    private FireStationService fireStationService;

    @Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/firestations")
    public String getAllFireStations() {
        log.info("HTTP GET request received for getAllFireStations");
        return JsonStream.serialize(fireStationService.getAllFireStations());
    }

    @GetMapping("/firestation/{address}")
    public String getFireStationByAddress(@PathVariable("address") String address) {
        log.info("HTTP GET request received for getFireStationByAddress: {}", address);
        try {
            log.info("HTTP GET request received for getFireStationByAddress, SUCCESS");
            return JsonStream.serialize(fireStationService.getFireStationByAddress(address));
        }
        catch (NotFoundException e) {
            log.error("HTTP GET request received for getFireStationByAddress, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station not found", e);
        }
    }

    @PostMapping("/firestation")
    @ResponseStatus(HttpStatus.CREATED)
    public String createFireStation(@RequestBody FireStation fireStation) {
        log.info("HTTP POST request received for createFireStation: {}", fireStation.getAddress());
        try {
            log.info("HTTP POST request received for createFireStation, SUCCESS");
            return JsonStream.serialize(fireStationService.createFireStation(fireStation));
        }
        catch (DuplicateException e) {
            log.error("HTTP POST request received for createFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Fire Station already exists", e);
        }
    }

    @PutMapping("/firestation/{address}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFireStation(@RequestBody FireStation fireStation, @PathVariable("address") String address) {
        log.info("HTTP PUT request received for updateFireStation: {}", address);
        try {
            log.info("HTTP PUT request received for updateFireStation, SUCCESS");
            fireStationService.updateFireStation(fireStation);
        } catch (NotFoundException e) {
            log.error("HTTP PUT request received for updateFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station does not exist", e);
        }
    }

    @DeleteMapping("/firestation/{address}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFireStation(@PathVariable("address") String address) {
        log.info("HTTP DELETE request received for deleteFireStation: {}", address);
        try {
            log.info("HTTP DELETE request received for deleteFireStation, SUCCESS");
            fireStationService.deleteFireStation(address);
        }
        catch (NotFoundException e) {
            log.error("HTTP DELETE request received for deleteFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station does not exist", e);
        }
    }

    @GetMapping("/firestation")
    public String getPersonsByStation(@RequestParam("stationNumber") String stationNumber) {
        log.info("HTTP GET request received for getPersonsByStation: {}", stationNumber);
        try {
            log.info("HTTP GET request received for getPersonsByStation, SUCCESS");
            return JsonStream.serialize(fireStationService.getListAdultsAndChildrenByStationNumber(stationNumber));
        }
        catch (NotFoundException e) {
            log.error("HTTP GET request received for getPersonsByStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station not found", e);
        }
    }

    @GetMapping("/fire")
    public String getStationAndPersonsByAddress(@RequestParam("address") String address) {
        log.info("HTTP GET request received for getStationAndPersonsByAddress: {}", address);
        try {
            log.info("HTTP GET request received for getStationAndPersonsByAddress, SUCCESS");
            return JsonStream.serialize(fireStationService.getStationAndPersonsByAddress(address));
        }
        catch (NotFoundException e) {
            log.error("HTTP GET request received for getStationAndPersonsByAddress, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found", e);
        }
    }

    //TODO
    @GetMapping("/flood/stations")
    public String getHouseholdsByStationList(@RequestParam List<String> stations) {
        log.info("HTTP GET request received for getHouseholdsByStationList: {}", Arrays.toString(stations.toArray()));
        try {
            log.info("HTTP GET request received for getHouseholdsByStationList, SUCCESS");
            return JsonStream.serialize(fireStationService.getHouseholdsByStationList(stations));
        }
        catch (NotFoundException e) {
            log.error("HTTP GET request received for getHouseholdsByStationList, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station(s) not found", e);
        }
    }

    @GetMapping("/phoneAlert")
    public String getPhoneNumbersByStation(@RequestParam("firestation") String stationNumber) {
        log.info("HTTP GET request received for getPhoneNumbersByStation: {}", stationNumber);
        try {
            log.info("HTTP GET request received for getPhoneNumbersByStation, SUCCESS");
            return JsonStream.serialize(fireStationService.getPhoneNumbersByStationNumber(stationNumber));
        }
        catch (NotFoundException e) {
            log.error("HTTP GET request received for getPhoneNumbersByStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station not found", e);
        }
    }
}