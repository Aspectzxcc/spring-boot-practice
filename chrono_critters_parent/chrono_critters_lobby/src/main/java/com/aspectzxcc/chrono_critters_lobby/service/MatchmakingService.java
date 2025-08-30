package com.aspectzxcc.chrono_critters_lobby.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class MatchmakingService {
    private final Queue<String> queue = new LinkedList<>();

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
            String s1 = queue.poll();
            String s2 = queue.poll();
            return List.of(s1, s2);
        }
        return List.of();
    }

    public synchronized List<String> getQueue() {
        return List.copyOf(queue);
    }
}