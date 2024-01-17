package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;

public interface PlayerService {
    PlayerCreationDTO createPlayer(PlayerCreationDTO playerCreationDTO);
}
