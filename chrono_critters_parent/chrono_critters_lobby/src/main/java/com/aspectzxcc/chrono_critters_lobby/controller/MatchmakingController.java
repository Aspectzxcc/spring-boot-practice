package com.aspectzxcc.chrono_critters_lobby.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.aspectzxcc.chrono_critters_lobby.service.MatchmakingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MatchmakingController {

    private final MatchmakingService matchmakingService;
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/matchmaking/join")
    @SendTo("/topic/queue")
    public List<String> joinQueue(@Payload String playerId) {
        matchmakingService.joinQueue(playerId);
        List<String> match = matchmakingService.tryMatch();
        if (!match.isEmpty()) {
            messagingTemplate.convertAndSend("/topic/match", match);
        }
        return matchmakingService.getQueue();
    }

    @MessageMapping("/matchmaking/leave")
    @SendTo("/topic/queue")
    public List<String> leaveQueue(@Payload String playerId) {
        matchmakingService.leaveQueue(playerId);
        return matchmakingService.getQueue();
    }
}