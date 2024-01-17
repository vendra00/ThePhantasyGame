package com.carbon.thephantasyrpg.enums;

import lombok.Getter;

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

    public static Races valueOfId(long id) {
        for (Races race : values()) {
            if (race.getId() == id) {
                return race;
            }
        }
        throw new IllegalArgumentException("Invalid Race ID: " + id);
    }
    }

