package com.carbon.thephantasyrpg.dto;

import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.model.BasicAttributes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerCreationDTO {
    private String name;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private Races race;
}
