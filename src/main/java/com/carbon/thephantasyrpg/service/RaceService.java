package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.model.Race;

import java.util.Map;

public interface RaceService {
    Race getRaceById(Long id);

    Map<Races, Map<String, Double>> fetchRaceAttributes();
}
