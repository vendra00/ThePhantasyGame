package com.carbon.thephantasyrpg.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Implementation of the DiceService interface
 */
@Service
public class DiceServiceImpl implements DiceService {

    // This is a Random object
    private final Random random = new Random();

    @Override
    public int roll(int sides) {
        return random.nextInt(sides) + 1;
    }
}

