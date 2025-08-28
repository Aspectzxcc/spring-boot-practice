package com.aspectzxcc.chrono_critters_lobby.listener;

import com.aspectzxcc.chrono_critters_lobby.service.LobbyService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import com.aspectzxcc.chrono_critters_lobby.model.PlayerInfo;

@Component
@RequiredArgsConstructor
public class LobbyDisconnectListener {

    private final LobbyService lobbyService;
    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();

        Optional.ofNullable(sessionId).ifPresent(id -> {
            List<PlayerInfo> players = lobbyService.getPlayers();
            players.removeIf(p -> id.equals(p.getId()));
            messagingTemplate.convertAndSend("/topic/lobby", players);
        });
    }
}