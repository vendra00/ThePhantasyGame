package com.carbon.thephantasyrpg.controller;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

    private final PlayerService playerService;
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // POST request to create a new player
    @PostMapping("/players")
    public ResponseEntity<PlayerCreationDTO> createPlayer(@RequestBody PlayerCreationDTO playerCreationDTO) {
        PlayerCreationDTO savedPlayerDTO = playerService.createPlayer(playerCreationDTO);
        return new ResponseEntity<>(savedPlayerDTO, HttpStatus.CREATED);
    }
}
