package com.carbon.thephantasyrpg.model.dice;

import com.carbon.thephantasyrpg.enums.DiceType;
import com.carbon.thephantasyrpg.service.DiceService;

/**
 * A 10-sided dice
 */
public class D10 extends Dice {
    private final DiceService diceService;

    /**
     * Constructor for a 10-sided dice.
     * @param diceService the dice service to use for rolling the dice.
     */
    public D10(DiceService diceService) {
        super(DiceType.D10, diceService); // 10-sided dice
        this.diceService = diceService;
    }

    @Override
    public int roll() {
        return diceService.roll(getSides());
    }
}
