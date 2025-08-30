package com.aspectzxcc.chrono_critters_lobby.controller;

import java.util.List;
import java.util.Map;

import org.springframework.messaging.handler.annotation.Headers;
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
    public List<String> joinQueue(@Payload String playerId, @Headers Map<String, Object> headers) {
        if (playerId != null) {
            matchmakingService.joinQueue(playerId);
        }
        List<String> match = matchmakingService.tryMatch();
        if (!match.isEmpty()) {
            for (String matchedPlayerId : match) {
                messagingTemplate.convertAndSendToUser(
                    matchedPlayerId,
                    "/match",
                    match
                );
            }
        }
        return matchmakingService.getQueue();
    }

    @MessageMapping("/matchmaking/leave")
    @SendTo("/topic/queue")
    public List<String> leaveQueue(@Payload String playerId, @Headers Map<String, Object> headers) {
        if (playerId != null) {
            matchmakingService.leaveQueue(playerId);
        }
        return matchmakingService.getQueue();
    }
}