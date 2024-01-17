package com.carbon.thephantasyrpg.model;

import com.carbon.thephantasyrpg.enums.Races;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Race {
    @Id
    private Long id;

    private String name;
    private String description;

    @Embedded
    private RaceBasicAttributeModifiers basicAttributeModifiers;

    @ElementCollection
    private List<Perk> perks;

    @PostLoad
    private void fillTransient() {
        if (id > 0) {
            this.name = Races.valueOfId(id).name();
        }
    }

    @PrePersist
    @PreUpdate
    private void fillPersistent() {
        if (name != null) {
            this.id = Races.valueOf(name).getId();
        }
    }
}
