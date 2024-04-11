package com.carbon.thephantasyrpg.controller;

import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/race")
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

    @GetMapping("/race-description/{raceId}")
    public ResponseEntity<String> fetchRaceDescription(@PathVariable("raceId") Long raceId) {
        Races race = Races.valueOfId(raceId); // Assuming you have a method to get enum value by ID
        String description = raceService.fetchRaceDescription(race);
        if (description != null && !description.isEmpty()) {
            return ResponseEntity.ok(description);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
