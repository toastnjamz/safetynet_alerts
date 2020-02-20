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

@RestController
//@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger log = LoggerFactory.getLogger(FireStationController.class);

    private FireStationService fireStationService;

    @Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/firestations")
    public String getAllFireStations() {
        log.info("GET request made for getAllFireStations");
        return JsonStream.serialize(fireStationService.getAllFireStations());
    }

    @GetMapping("/firestation/{address}")
    public String getFireStationByAddress(@PathVariable("address") String address) {
        log.info("GET request made for getFireStationByAddress: " + address);
        try {
            log.info("GET called for getFireStationByAddress, SUCCESS");
            return JsonStream.serialize(fireStationService.getFireStationByAddress(address));
        }
        catch (NotFoundException e) {
            log.error("GET called for getFireStationByAddress, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station not found", e);
        }
    }

    @PostMapping("/firestation")
    @ResponseStatus(HttpStatus.CREATED)
    public String createFireStation(@RequestBody FireStation fireStation) {
        log.info("POST request made for createFireStation: " + fireStation.getAddress());
        try {
            log.info("POST called for createFireStation, SUCCESS");
            return JsonStream.serialize(fireStationService.createFireStation(fireStation));
        }
        catch (DuplicateException e) {
            log.error("POST called for createFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Fire Station already exists", e);
        }
    }

    @PutMapping("/firestation/{address}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFireStation(@RequestBody FireStation fireStation, @PathVariable("address") String address) {
        log.info("PUT request made for updateFireStation: " + address);
        try {
            log.info("PUT called for updateFireStation, SUCCESS");
            fireStationService.updateFireStation(fireStation);
        } catch (NotFoundException e) {
            log.error("PUT called for updateFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station does not exist", e);
        }
    }

    @DeleteMapping("/firestation/{address}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFireStation(@PathVariable("address") String address) {
        log.info("DELETE request made for deleteFireStation: " + address);
        try {
            log.info("DELETE called for deleteFireStation, SUCCESS");
            fireStationService.deleteFireStation(address);
        }
        catch (NotFoundException e) {
            log.error("DELETE called for deleteFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station does not exist", e);
        }
    }

    //TODO
    @GetMapping("/firestation")
    public String getPersonsByStation(@RequestParam("stationNumber") String stationNumber) {
        log.info("GET request made for getPersonsByStation: " + stationNumber);
        try {
            log.info("GET called for getPersonsByStation, SUCCESS");
            return JsonStream.serialize(fireStationService.getListAdultsAndChildrenByStationNumber(stationNumber));
        }
        catch (NotFoundException e) {
            log.error("GET called for getPersonsByStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station not found", e);
        }
    }

    @GetMapping("/phoneAlert")
    public String getPhoneNumbersByStation(@RequestParam("firestation") String stationNumber) {
        log.info("GET request made for getPhoneNumbersByStation: " + stationNumber);
        try {
            log.info("GET called for getPhoneNumbersByStation, SUCCESS");
            return JsonStream.serialize(fireStationService.getPhoneNumbersByStationNumber(stationNumber));
        }
        catch (NotFoundException e) {
            log.error("GET called for getPhoneNumbersByStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station not found", e);
        }
    }
}