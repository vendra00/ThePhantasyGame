package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Player extends Creature {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
