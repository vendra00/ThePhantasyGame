package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.mapper.PlayerMapper;
import com.carbon.thephantasyrpg.model.Player;
import com.carbon.thephantasyrpg.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.carbon.thephantasyrpg.utils.PlayerServiceUtils.calculatePlayerAttributes;
import static com.carbon.thephantasyrpg.utils.PlayerServiceUtils.calculatePlayerHealManaStamina;

/**
 * Implementation of the PlayerService interface
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    // This is the PlayerRepository
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final RaceService raceService;

    /**
     * Constructor for the PlayerServiceImpl class
     * @param playerRepository the PlayerRepository object
     * @param playerMapper the PlayerMapper object
     * @param raceService the RaceService object
     */
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper, RaceService raceService) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.raceService = raceService;
    }

    @Override
    public PlayerCreationDTO createPlayer(PlayerCreationDTO playerCreationDTO) {
        Player player = playerMapper.playerCreationDTOToPlayer(playerCreationDTO, raceService);
        calculatePlayerAttributes(playerCreationDTO, player);
        calculatePlayerHealManaStamina(player);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.playerToPlayerCreationDTO(savedPlayer);
    }

}
