package com.aspectzxcc.chrono_critters_lobby.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.aspectzxcc.chrono_critters_lobby.model.LobbyEvent;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.util.Optional;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = Optional.ofNullable(headerAccessor.getSessionAttributes())
            .map(attrs -> (String) attrs.get("username"))
            .orElse("Anonymous");

        // Always broadcast the disconnect event
        LobbyEvent disconnectEvent = new LobbyEvent();
        disconnectEvent.setUsername(username);
        disconnectEvent.setMessage("left the lobby");
        simpMessagingTemplate.convertAndSend("/topic/lobby", disconnectEvent);
    }
}