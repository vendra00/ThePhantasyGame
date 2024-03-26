package com.carbon.thephantasyrpg.service;

public interface DiceService {

    /**
     * Roll a dice with the given number of sides
     * @param sides The number of sides on the dice
     * @return The result of the roll
     */
    int roll(int sides);
}

