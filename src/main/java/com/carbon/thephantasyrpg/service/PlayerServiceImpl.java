package com.carbon.thephantasyrpg.service;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.mapper.PlayerMapper;
import com.carbon.thephantasyrpg.model.Player;
import com.carbon.thephantasyrpg.model.User;
import com.carbon.thephantasyrpg.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    private final UserService userService;

    /**
     * Constructor for the PlayerServiceImpl class
     * @param playerRepository the PlayerRepository object
     * @param playerMapper the PlayerMapper object
     * @param raceService the RaceService object
     * @param userService the UserService object
     */
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper, RaceService raceService, UserService userService) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.raceService = raceService;
        this.userService = userService;
    }

    @Override
    public PlayerCreationDTO createPlayer(PlayerCreationDTO playerCreationDTO) {
        Player player = playerMapper.playerCreationDTOToPlayer(playerCreationDTO, raceService);
        calculatePlayerAttributes(playerCreationDTO, player);
        calculatePlayerHealManaStamina(player);

        // Retrieve the currently logged-in user's username from the security context
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Use userService to get the User object
        Optional<User> userOpt = userService.findByUsername(username);
        User user = userOpt.orElseThrow(() -> new IllegalStateException("User not found for username: " + username));

        // Set the User to the Player
        player.setUser(user);

        Player savedPlayer = playerRepository.save(player);
        return playerMapper.playerToPlayerCreationDTO(savedPlayer);
    }

}
