package com.safetynet.alerts.controller;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/Firestation")
    public String getFireStationByAddress(@NotNull @RequestParam String address,
                                          HttpServletResponse response) {
        log.debug("HTTP GET request received for getFireStationByAddress: {}", address);
        Optional<FireStation> stationOptional = Optional.ofNullable(fireStationService.getFireStationByAddress(address));
        if (stationOptional.isPresent()) {
            log.info("HTTP GET request received for getFireStationByAddress, SUCCESS");
            return JsonStream.serialize(fireStationService.getFireStationByAddress(address));
        }
        else {
            log.error("HTTP GET request received for getFireStationByAddress, ERROR");
            response.setStatus(404);
            return String.format("Station not found for address %s", address);
        }
    }

    @PostMapping("/firestation")
    public String createFireStation(@NotNull @RequestBody FireStation fireStation,
                                    HttpServletResponse response) {
        log.debug("HTTP POST request received for createFireStation: {}", fireStation.getAddress());
        Optional<FireStation> stationOptional = Optional.ofNullable(fireStationService.getFireStationByAddress(fireStation.getAddress()));
        if (!stationOptional.isPresent()) {
            log.info("HTTP POST request received for createFireStation, SUCCESS");
            response.setStatus(201);
            return JsonStream.serialize(fireStationService.createFireStation(fireStation));
        }
        else {
            log.error("HTTP POST request received for createFireStation, ERROR");
            response.setStatus(409);
            return String.format("Station for address %s already exists", fireStation.getAddress());
        }
    }

    @PutMapping("/firestation")
    public void updateFireStation(@NotNull @RequestBody FireStation fireStation,
                                  @NotNull @RequestParam String address,
                                  HttpServletResponse response) {
        log.debug("HTTP PUT request received for updateFireStation: {}", address);
        Optional<FireStation> stationOptional = Optional.ofNullable(fireStationService.getFireStationByAddress(address));
        if (stationOptional.isPresent()) {
            log.info("HTTP PUT request received for updateFireStation, SUCCESS");
            response.setStatus(200);
            fireStationService.updateFireStation(fireStation);
        }
        else {
            log.error("HTTP PUT request received for updateFireStation, ERROR");
            response.setStatus(404);
        }
    }

    @DeleteMapping("/firestation")
    public void deleteFireStation(@NotNull @RequestParam String address,
                                  HttpServletResponse response) {
        log.debug("HTTP DELETE request received for deleteFireStation: {}", address);
        Optional<FireStation> stationOptional = Optional.ofNullable(fireStationService.getFireStationByAddress(address));
        if (stationOptional.isPresent()) {
            log.info("HTTP DELETE request received for deleteFireStation, SUCCESS");
            response.setStatus(200);
            fireStationService.deleteFireStation(address);
        }
        else {
            log.error("HTTP DELETE request received for deleteFireStation, ERROR");
            response.setStatus(404);
        }
    }

    @GetMapping("/firestation")
    public String getPersonsByStation(@NotNull @RequestParam("stationNumber") String stationNumber,
                                      HttpServletResponse response) {
        log.debug("HTTP GET request received for getPersonsByStation: {}", stationNumber);
        if (fireStationService.getListAdultsAndChildrenByStationNumber(stationNumber).getFormattedPersonList().size() != 0) {
            log.info("HTTP GET request received for getPersonsByStation, SUCCESS");
            return JsonStream.serialize(fireStationService.getListAdultsAndChildrenByStationNumber(stationNumber));
        }
        else {
            log.error("HTTP GET request received for getPersonsByStation, ERROR");
            response.setStatus(404);
            return String.format("Persons not found for station number %s", stationNumber);
        }
    }

    @GetMapping("/fire")
    public String getStationAndPersonsByAddress(@NotNull @RequestParam("address") String address,
                                                HttpServletResponse response) {
        log.debug("HTTP GET request received for getStationAndPersonsByAddress: {}", address);
        if (!fireStationService.getStationAndPersonsByAddress(address).isEmpty()) {
            log.info("HTTP GET request received for getStationAndPersonsByAddress, SUCCESS");
            return JsonStream.serialize(fireStationService.getStationAndPersonsByAddress(address));
        }
        else {
            log.error("HTTP GET request received for getStationAndPersonsByAddress, ERROR");
            response.setStatus(404);
            return String.format("Station and persons not found for address %s", address);
        }
    }

    @GetMapping("/flood/stations")
    public String getHouseholdsByStationList(@NotNull @RequestParam List<String> stations,
                                             HttpServletResponse response) {
        log.debug("HTTP GET request received for getHouseholdsByStationList: {}", Arrays.toString(stations.toArray()));
        if (!fireStationService.getHouseholdsByStationList(stations).isEmpty()) {
            log.info("HTTP GET request received for getHouseholdsByStationList, SUCCESS");
            return JsonStream.serialize(fireStationService.getHouseholdsByStationList(stations));
        }
        else {
            log.error("HTTP GET request received for getHouseholdsByStationList, ERROR");
            response.setStatus(404);
            return String.format("No household info found for %s", stations);
        }
    }

    @GetMapping("/phoneAlert")
    public String getPhoneNumbersByStation(@NotNull @RequestParam("firestation") String stationNumber,
                                           HttpServletResponse response) {
        log.debug("HTTP GET request received for getPhoneNumbersByStation: {}", stationNumber);
        if (!fireStationService.getPhoneNumbersByStationNumber(stationNumber).isEmpty()) {
            log.info("HTTP GET request received for getPhoneNumbersByStation, SUCCESS");
            return JsonStream.serialize(fireStationService.getPhoneNumbersByStationNumber(stationNumber));
        }
        else {
            log.error("HTTP GET request received for getPhoneNumbersByStation, ERROR");
            response.setStatus(404);
            return String.format("No phone numbers found for station number %s", stationNumber);
        }
    }
}