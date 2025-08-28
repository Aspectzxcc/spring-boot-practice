package com.aspectzxcc.chrono_critters_lobby.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.aspectzxcc.chrono_critters_lobby.model.LobbyEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        LobbyEvent disconnectEvent = new LobbyEvent();
        disconnectEvent.setUsername("Anonymous");
        disconnectEvent.setMessage("Left the lobby");

        simpMessagingTemplate.convertAndSend("/topic/lobby", disconnectEvent);
    }
}