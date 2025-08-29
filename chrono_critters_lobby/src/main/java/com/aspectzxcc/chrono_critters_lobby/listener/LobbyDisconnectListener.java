package com.aspectzxcc.chrono_critters_lobby.listener;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.aspectzxcc.chrono_critters_lobby.service.LobbyService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LobbyDisconnectListener {

    private final LobbyService lobbyService;
    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = sha.getSessionId();
        lobbyService.removePlayerBySessionId(sessionId);
        messagingTemplate.convertAndSend("/topic/lobby", lobbyService.getPlayerIds());
    }
}