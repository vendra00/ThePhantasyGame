package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.model.Race;

import java.util.Map;

/**
 * RaceService interface
 */
public interface RaceService {
    Race getRaceById(Long id);

    /**
     * Fetches the attributes modifiers of a race
     * @return attributes modifiers from a given race
     */
    Map<Races, Map<String, Double>> fetchRaceAttributes();

    /**
     * Fetches the description of a given Race
     * @param race Race to fetch the description of
     * @return Description of the given race
     */
    String fetchRaceDescription(Races race);
}
