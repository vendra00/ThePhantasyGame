package com.carbon.thephantasyrpg.model.dice;

import com.carbon.thephantasyrpg.enums.DiceType;
import com.carbon.thephantasyrpg.service.DiceService;

/**
 * A 6-sided dice
 */
public class D6 extends Dice {

    private final DiceService diceService;

    /**
     * Constructor for D6
     * @param diceService the dice service to use for rolling the dice
     */
    public D6(DiceService diceService) {
        super(DiceType.D6, diceService);
        this.diceService = diceService;
    }

    @Override
    public int roll() {
        return diceService.roll(getSides());
    }
}
