package com.aspectzxcc.chrono_critters_lobby.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BattleProxyService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String gameLogicUrl = "http://localhost:8082/battle"; // Change port as needed

    public Object createBattle(String battleId, String player1Id, String player2Id) {
        String url = String.format("%s?battleId=%s&player1Id=%s&player2Id=%s", gameLogicUrl, battleId, player1Id, player2Id);
        ResponseEntity<Object> response = restTemplate.postForEntity(url, null, Object.class);
        return response.getBody();
    }
}