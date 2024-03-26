package com.carbon.thephantasyrpg.enums;

import lombok.Getter;

/**
 * Represents the different types of dice that can be rolled.
 */
@Getter
public enum DiceType {
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20);

    private final int sides;

    DiceType(int sides) {
        this.sides = sides;
    }
}

