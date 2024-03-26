package com.carbon.thephantasyrpg.model.dice;

import com.carbon.thephantasyrpg.enums.DiceType;
import com.carbon.thephantasyrpg.service.DiceService;

/**
 * A 12-sided dice
 */
public class D12 extends Dice {
    private final DiceService diceService;

    /**
     * Constructor for a 10-sided dice.
     * @param diceService the dice service to use for rolling the dice.
     */
    public D12(DiceService diceService) {
        super(DiceType.D12, diceService); // 12-sided dice
        this.diceService = diceService;
    }

    @Override
    public int roll() {
        return diceService.roll(getSides());
    }
}
