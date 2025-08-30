package com.aspectzxcc.chrono_critters_lobby.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class HandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // Extract playerId from query string
        String query = request.getURI().getQuery();
        String playerId = null;
        if (query != null && query.startsWith("playerId=")) {
            playerId = query.substring("playerId=".length());
        }
        if (playerId == null || playerId.isEmpty()) {
            // Fallback: generate a random ID
            playerId = java.util.UUID.randomUUID().toString();
        }
        final String finalPlayerId = playerId;
        return () -> finalPlayerId;
    }
}
