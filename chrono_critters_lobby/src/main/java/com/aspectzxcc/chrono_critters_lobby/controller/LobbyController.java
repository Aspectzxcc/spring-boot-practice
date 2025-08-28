package com.aspectzxcc.chrono_critters_lobby.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.aspectzxcc.chrono_critters_lobby.model.LobbyEvent;

@Controller
public class LobbyController {

    @MessageMapping("/lobby/join")
    @SendTo("/topic/lobby")
    public LobbyEvent joinLobby(LobbyEvent event) {
        // Handle user joining lobby, broadcast to all clients
        return event;
    }

    @MessageMapping("/lobby/message")
    @SendTo("/topic/lobby")
    public LobbyEvent sendMessage(LobbyEvent event) {
        // Handle chat or other lobby events
        return event;
    }
}