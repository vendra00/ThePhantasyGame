package com.carbon.thephantasyrpg.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Enum to represent the different races a NPC or Player can be
 */
@Getter
public enum Races {
    HUMAN(1L),
    ELF(2L),
    DWARF(3L),
    ORC(4L);

    private final long id;

    Races(long id) {
        this.id = Math.toIntExact(id);
    }

    /**
     * Method to get the display name of the race with underscores replaced with spaces and words capitalized
     * @return the display name of the race
     */
    public String getDisplayName() {
        return StringUtils.capitalize(this.name().toLowerCase().replace('_', ' '));
    }

    /**
     * Method to get the proper race with its id from the database
     * @param id the id of the race for the database
     * @return a race with the given id
     */
    public static Races valueOfId(long id) {
        for (Races race : values()) {
            if (race.getId() == id) {
                return race;
            }
        }
        throw new IllegalArgumentException("Invalid Race ID: " + id);
    }
    }

