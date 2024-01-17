package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.mapper.PlayerMapper;
import com.carbon.thephantasyrpg.model.Player;
import com.carbon.thephantasyrpg.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.carbon.thephantasyrpg.utils.PlayerServiceUtils.calculatePlayerAttributes;
import static com.carbon.thephantasyrpg.utils.PlayerServiceUtils.calculatePlayerHealManaStamina;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final RaceService raceService;

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
