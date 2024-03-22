package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.enums.RaceServiceI18N;
import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.model.Race;
import com.carbon.thephantasyrpg.repository.RaceRepository;
import com.carbon.thephantasyrpg.utils.MessageUtils;
import com.carbon.thephantasyrpg.utils.RaceServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of the RaceService interface
 */
@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final RaceServiceUtils raceServiceUtils;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, MessageUtils messageUtils) {
        this.raceRepository = raceRepository;
        this.raceServiceUtils = new RaceServiceUtils(messageUtils);
    }

    @Override
    public Race getRaceById(Long id) {
        return raceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Race ID: " + id)
        );
    }

    @Override
    public Map<Races, Map<String, Double>> fetchRaceAttributes() {
        List<Object[]> results = raceRepository.findAllAttributes();
        Map<Races, Map<String, Double>> raceAttributes = new HashMap<>();

        // Iterate through the results and convert them to a map
        for (Object[] result : results) {
            String raceName = (String) result[0];
            // Manually convert each attribute to Double
            Double charismaModifier = raceServiceUtils.convertToDouble(result[1]);
            Double constitutionModifier = raceServiceUtils.convertToDouble(result[2]);
            Double dexterityModifier = raceServiceUtils.convertToDouble(result[3]);
            Double intelligenceModifier = raceServiceUtils.convertToDouble(result[4]);
            Double strengthModifier = raceServiceUtils.convertToDouble(result[5]);
            Double wisdomModifier = raceServiceUtils.convertToDouble(result[6]);

            Races raceEnum = raceServiceUtils.convertStringToRaceEnum(raceName);
            if (raceEnum == null) continue; // Skip if conversion failed

            Map<String, Double> attributes = Map.of(
                    raceServiceUtils.getMessage(RaceServiceI18N.CHARISMA_MODIFIER), Objects.requireNonNull(charismaModifier, raceServiceUtils.getMessage(RaceServiceI18N.CHARISMA_MODIFIER_IS_NULL) + " " + raceName),
                    raceServiceUtils.getMessage(RaceServiceI18N.CONSTITUTION_MODIFIER), Objects.requireNonNull(constitutionModifier, raceServiceUtils.getMessage(RaceServiceI18N.CONSTITUTION_MODIFIER_IS_NULL) + " " + raceName),
                    raceServiceUtils.getMessage(RaceServiceI18N.DEXTERITY_MODIFIER), Objects.requireNonNull(dexterityModifier, raceServiceUtils.getMessage(RaceServiceI18N.DEXTERITY_MODIFIER_IS_NULL) + " " + raceName),
                    raceServiceUtils.getMessage(RaceServiceI18N.INTELLIGENCE_MODIFIER), Objects.requireNonNull(intelligenceModifier, raceServiceUtils.getMessage(RaceServiceI18N.INTELLIGENCE_MODIFIER_IS_NULL) + " " + raceName),
                    raceServiceUtils.getMessage(RaceServiceI18N.STRENGTH_MODIFIER), Objects.requireNonNull(strengthModifier, raceServiceUtils.getMessage(RaceServiceI18N.STRENGTH_MODIFIER_IS_NULL) + " " + raceName),
                    raceServiceUtils.getMessage(RaceServiceI18N.WISDOM_MODIFIER), Objects.requireNonNull(wisdomModifier, raceServiceUtils.getMessage(RaceServiceI18N.WISDOM_MODIFIER_IS_NULL) + " " + raceName)
            );

            raceAttributes.put(raceEnum, attributes);
        }

        return raceAttributes;
    }

    @Override
    public String fetchRaceDescription(Races race) {
        Optional<Race> raceOptional = raceRepository.findById(race.getId());
        return raceOptional.map(Race::getDescription).orElse("Description not available.");
    }

}
