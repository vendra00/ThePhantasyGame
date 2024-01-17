package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class NPC extends Creature {
    // Additional fields specific to NPC
}
