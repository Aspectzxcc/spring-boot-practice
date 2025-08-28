package com.aspectzxcc.chrono_critters_lobby.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.aspectzxcc.chrono_critters_lobby.model.LobbyEvent;

@Controller
public class LobbyController {

    @MessageMapping("/lobby/join")
    @SendTo("/topic/lobby")
    public LobbyEvent joinLobby(@Payload LobbyEvent event, @Headers Map<String, Object> headers) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap((org.springframework.messaging.Message<?>) headers.get("simpMessage"));
        Optional.ofNullable(accessor.getSessionAttributes())
            .ifPresent(attrs -> Optional.ofNullable(event.getUsername())
            .ifPresent(username -> attrs.put("username", username)));
        return event;
    }

    @MessageMapping("/lobby/message")
    @SendTo("/topic/lobby")
    public LobbyEvent sendMessage(LobbyEvent event) {
        return event;
    }
}