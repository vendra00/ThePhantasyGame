package com.carbon.thephantasyrpg.model.dice;

import com.carbon.thephantasyrpg.enums.DiceType;
import com.carbon.thephantasyrpg.service.DiceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Dice {
    protected final DiceType type;
    protected final DiceService diceService;

    public abstract int roll();

    public int getSides() {
        return type.getSides();
    }
}

