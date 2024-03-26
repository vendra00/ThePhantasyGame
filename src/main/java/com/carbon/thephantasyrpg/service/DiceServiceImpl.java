package com.carbon.thephantasyrpg.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceServiceImpl implements DiceService {

    private final Random random = new Random();

    @Override
    public int roll(int sides) {
        return random.nextInt(sides) + 1;
    }
}

