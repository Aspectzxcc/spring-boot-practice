package com.aspectzxcc.chrono_critters.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aspectzxcc.chrono_critters.model.Player;
import com.aspectzxcc.chrono_critters.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }
    
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    
    public Optional<Player> getPlayerById(String id) {
        return playerRepository.findById(id);
    }
    
    public void deletePlayer(String id) {
        playerRepository.deleteById(id);
    }
}
