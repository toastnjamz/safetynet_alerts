package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.exception.DuplicateFireStationException;
import com.safetynet.alerts.exception.FireStationNotFoundException;
import com.safetynet.alerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger log = LoggerFactory.getLogger(FireStationController.class);

    private FireStationService fireStationService;

    @Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping
    public String getAllFireStations() {
        log.info("GET request made for getAllFireStations");
        return fireStationService.getAllFireStations();
    }

    @GetMapping("/{address}")
    public String getFireStationByAddress(@PathVariable("address") String address) {
        log.info("GET request made for getFireStationByAddress: " + address);
        try {
            log.info("GET called for getFireStationByAddress, SUCCESS");
            return fireStationService.getFireStationByAddress(address);
        }
        catch (FireStationNotFoundException e) {
            log.error("GET called for getFireStationByAddress, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station not found", e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createFireStation(@RequestBody FireStation fireStation) {
        log.info("POST request made for createFireStation: " + fireStation.getAddress());
        try {
            log.info("POST called for createFireStation, SUCCESS");
            return fireStationService.createFireStation(fireStation);
        }
        catch (DuplicateFireStationException e) {
            log.error("POST called for createFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Fire Station already exists", e);
        }
    }

    @PutMapping("/{address}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFireStation(@RequestBody FireStation fireStation, @PathVariable("address") String address) {
        log.info("PUT request made for updateFireStation: " + address);
        try {
            log.info("PUT called for updateFireStation, SUCCESS");
            fireStationService.updateFireStation(fireStation);
        } catch (FireStationNotFoundException e) {
            log.error("PUT called for updateFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station does not exist", e);
        }
    }

    @DeleteMapping("/{address}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFireStation(@PathVariable("address") String address) {
        log.info("DELETE request made for deleteFireStation: " + address);
        try {
            log.info("DELETE called for deleteFireStation, SUCCESS");
            fireStationService.deleteFireStation(address);
        }
        catch (FireStationNotFoundException e) {
            log.error("DELETE called for deleteFireStation, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fire Station does not exist", e);
        }
    }
}