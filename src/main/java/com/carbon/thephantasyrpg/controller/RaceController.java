package com.carbon.thephantasyrpg.controller;

import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RaceController {

    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping("/race-attributes")
    public ResponseEntity<Map<Races, Map<String, Double>>> fetchRaceAttributes() {
        Map<Races, Map<String, Double>> attributes = raceService.fetchRaceAttributes();
        if (!attributes.isEmpty()) {
            return ResponseEntity.ok(attributes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
