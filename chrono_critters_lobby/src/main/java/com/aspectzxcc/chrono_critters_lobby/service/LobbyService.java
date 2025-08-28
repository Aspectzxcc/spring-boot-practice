package com.aspectzxcc.chrono_critters_lobby.service;

import com.aspectzxcc.chrono_critters_lobby.model.PlayerInfo;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
public class LobbyService {
    private final List<PlayerInfo> waitingPlayers = new CopyOnWriteArrayList<>();

    public List<PlayerInfo> getPlayers() {
        return waitingPlayers;
    }

    public void addPlayer(PlayerInfo player) {
        boolean exists = waitingPlayers.stream().anyMatch(p -> p.getId().equals(player.getId()));
        if (!exists) {
            waitingPlayers.add(player);
        }
    }

    public void removePlayerById(String id) {
        waitingPlayers.removeIf(p -> p.getId().equals(id));
    }
}