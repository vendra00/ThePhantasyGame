package com.carbon.thephantasyrpg.model.dice;

import com.carbon.thephantasyrpg.enums.DiceType;
import com.carbon.thephantasyrpg.service.DiceService;

/**
 * Represents a 8-sided dice.
 */
public class D8 extends Dice {
    private final DiceService diceService;

    /**
     * Constructor for a 8-sided dice.
     * @param diceService the dice service to use for rolling the dice.
     */
    public D8(DiceService diceService) {
        super(DiceType.D8, diceService); // 8-sided dice
        this.diceService = diceService;
    }

    @Override
    public int roll() {
        return diceService.roll(getSides());
    }
}
