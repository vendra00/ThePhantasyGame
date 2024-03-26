package com.carbon.thephantasyrpg.model.dice;

import com.carbon.thephantasyrpg.enums.DiceType;
import com.carbon.thephantasyrpg.service.DiceService;

/**
 * Represents a 20-sided dice.
 */
public class D20 extends Dice {
    private final DiceService diceService;

    /**
     * Constructor for a 20-sided dice.
     * @param diceService the dice service to use for rolling the dice.
     */
    public D20(DiceService diceService) {
        super(DiceType.D20, diceService); // 20-sided dice
        this.diceService = diceService;
    }

    @Override
    public int roll() {
        return diceService.roll(getSides());
    }
}
