package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.model.Race;
import com.carbon.thephantasyrpg.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public Race getRaceById(Long id) {
        return raceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Race ID: " + id)
        );
    }
}
