package com.aspectzxcc.chrono_critters_lobby.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Controller;

import com.aspectzxcc.chrono_critters_lobby.model.PlayerInfo;
import com.aspectzxcc.chrono_critters_lobby.service.LobbyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LobbyController {
    private final LobbyService lobbyService;

    @MessageMapping("/lobby/join")
    @SendTo("/topic/lobby")
    public List<PlayerInfo> joinLobby(@Headers MessageHeaders headers) {
        String sessionId = (String) headers.get("simpSessionId");
        if (sessionId != null) {
            PlayerInfo player = new PlayerInfo(sessionId, sessionId);
            lobbyService.addPlayer(player);
        }
        return lobbyService.getPlayers();
    }

    @MessageMapping("/lobby/leave")
    @SendTo("/topic/lobby")
    public List<PlayerInfo> leaveLobby(PlayerInfo player, @Headers MessageHeaders headers) {
        String sessionId = (String) headers.get("simpSessionId");
        if (sessionId != null) {
            lobbyService.removePlayerById(sessionId);
        } else {
            lobbyService.removePlayerById(player.getId());
        }
        return lobbyService.getPlayers();
    }

    @MessageMapping("/lobby/list")
    @SendTo("/topic/lobby")
    public List<PlayerInfo> getLobbyPlayers() {
        return lobbyService.getPlayers();
    }
}