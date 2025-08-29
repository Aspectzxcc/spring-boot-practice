package com.aspectzxcc.chrono_critters_lobby.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class MatchmakingService {
    private final Queue<String> queue = new LinkedList<>();
    private final Map<String, String> sessionToPlayer = new ConcurrentHashMap<>();

    public synchronized void bindSessionToPlayer(String sessionId, String playerId) {
        sessionToPlayer.put(sessionId, playerId);
    }

    public synchronized void unbindSession(String sessionId) {
        sessionToPlayer.remove(sessionId);
    }

    public synchronized String getPlayerIdBySession(String sessionId) {
        return sessionToPlayer.get(sessionId);
    }

    public synchronized void joinQueue(String playerId) {
        if (!queue.contains(playerId)) {
            queue.offer(playerId);
        }
    }

    public synchronized void leaveQueue(String playerId) {
        queue.remove(playerId);
    }

    public synchronized List<String> tryMatch() {
        if (queue.size() >= 2) {
            String p1 = queue.poll();
            String p2 = queue.poll();
            return List.of(p1, p2);
        }
        return List.of();
    }

    public synchronized List<String> getQueue() {
        return List.copyOf(queue);
    }
}