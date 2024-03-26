package com.carbon.thephantasyrpg.model.dice;

import com.carbon.thephantasyrpg.enums.DiceType;
import com.carbon.thephantasyrpg.service.DiceService;

/**
 * Represents a 4-sided die.
 */
public class D4 extends Dice {

    /**
     * Constructs a D4 object. Calls the Dice constructor with the D4 DiceType and the DiceService.
     * @param diceService the DiceService to be used to roll the dice.
     */
    public D4(DiceService diceService) {
        super(DiceType.D4, diceService);
    }

    @Override
    public int roll() {
        return diceService.roll(getSides());
    }
}

