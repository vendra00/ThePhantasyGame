package com.carbon.thephantasyrpg.mapper;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.model.Player;
import com.carbon.thephantasyrpg.model.Race;
import com.carbon.thephantasyrpg.service.RaceService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {RaceService.class})
public interface PlayerMapper {

    // Custom mapping methods with annotations
    @Named("mapRaceToRacesEnum")
    default Races mapRaceToRacesEnum(Race race) {
        if (race == null) {
            return null;
        }
        return Races.valueOfId(race.getId());
    }

    // Custom mapping method for Races enum to Race
    @Named("mapRacesEnumToRace")
    default Race mapRacesEnumToRace(Races races, @Context RaceService raceService) {
        if (races == null) {
            return null;
        }
        return raceService.getRaceById(races.getId());
    }

    // Mapping annotations using the custom methods
    @Mapping(target = "race", source = "race", qualifiedByName = "mapRaceToRacesEnum")
    PlayerCreationDTO playerToPlayerCreationDTO(Player player);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "body", ignore = true)
    @Mapping(target = "race", source = "race", qualifiedByName = "mapRacesEnumToRace")
    Player playerCreationDTOToPlayer(PlayerCreationDTO playerCreationDTO, @Context RaceService raceService);
}





