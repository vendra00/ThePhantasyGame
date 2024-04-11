package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;

/**
 * PlayerService interface for the Player Service
 */
public interface PlayerService {
    /**
     * Create a new player with the given PlayerCreationDTO object
     * @param playerCreationDTO the PlayerCreationDTO object
     * @return the PlayerCreationDTO object
     */
    PlayerCreationDTO createPlayer(PlayerCreationDTO playerCreationDTO);
}
