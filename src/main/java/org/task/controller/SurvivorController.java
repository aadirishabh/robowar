package org.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.task.dto.RobotDto;
import org.task.dto.SurvivorDataDto;
import org.task.entity.Survivor;
import org.task.service.SurvivorService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/survivors")
public class SurvivorController {

    @Autowired
    private SurvivorService service;

    @GetMapping("/getAllRobots")
    public ResponseEntity<List<RobotDto>> getRobotData() {
        return new ResponseEntity<>(service.getRobotData(), HttpStatus.OK);
    }

    @PostMapping("/addSurvivors")
    public ResponseEntity<Survivor> addSurvivorData(@RequestBody Survivor survivor) {
        return new ResponseEntity<>(service.addSurvivor(survivor), HttpStatus.OK);
    }

    @PutMapping("/updateLocation")
    public ResponseEntity<Survivor> updateLocation(@RequestBody Survivor survivor) {
        return new ResponseEntity<>(service.updateLocation(survivor), HttpStatus.OK);
    }

    @PostMapping("/markContaminated")
    public ResponseEntity<Boolean> markContaminationOfSurvivor(@RequestParam int survivorId) {
        return new ResponseEntity<>(service.markContamination(survivorId), HttpStatus.OK);
    }

    @GetMapping("/getSurvivorData")
    public ResponseEntity<SurvivorDataDto> getSurvivorsInfo() {
        return new ResponseEntity<>(service.getSurvivorsData(), HttpStatus.OK);
    }

    @GetMapping("/getSurvivor")
    public ResponseEntity<Survivor> getSurvivor(@RequestParam int id) {
        return new ResponseEntity<>(service.getSurvivorById(id), HttpStatus.OK);
    }
}
