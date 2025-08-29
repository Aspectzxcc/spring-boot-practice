package com.aspectzxcc.chrono_critters_lobby.controller;

import java.util.Set;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.aspectzxcc.chrono_critters_lobby.service.LobbyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LobbyController {

    private final LobbyService lobbyService;

    @MessageMapping("/lobby/join")
    @SendTo("/topic/lobby")
    public Set<String> join(@Header("simpSessionId") String sessionId, String playerId) {
        lobbyService.addPlayer(sessionId, playerId);
        return lobbyService.getPlayerIds();
    }

    @MessageMapping("/lobby/leave")
    @SendTo("/topic/lobby")
    public Set<String> leave(@Header("simpSessionId") String sessionId) {
        lobbyService.removePlayerBySessionId(sessionId);
        return lobbyService.getPlayerIds();
    }
}