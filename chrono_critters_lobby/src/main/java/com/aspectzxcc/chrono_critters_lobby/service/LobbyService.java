package com.aspectzxcc.chrono_critters_lobby.service;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class LobbyService {
    private final Map<String, String> sessionToPlayerId = new ConcurrentHashMap<>();

    public void addPlayer(String sessionId, String playerId) {
        sessionToPlayerId.put(sessionId, playerId);
    }

    public void removePlayerBySessionId(String sessionId) {
        sessionToPlayerId.remove(sessionId);
    }

    public Set<String> getPlayerIds() {
        return Collections.unmodifiableSet(Set.copyOf(sessionToPlayerId.values()));
    }

    public void clearLobby() {
        sessionToPlayerId.clear();
    }
}