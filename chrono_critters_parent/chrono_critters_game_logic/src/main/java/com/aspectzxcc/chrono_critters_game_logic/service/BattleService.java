package com.aspectzxcc.chrono_critters_game_logic.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.aspectzxcc.chrono_critters_game_logic.model.BattleState;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BattleService {
    public final Map<String, BattleState> battleStates = new ConcurrentHashMap<>();

    // Create a new battle (called by lobby service)
    public BattleState createBattle(String battleId, String player1Id, String player2Id) {
        // TODO: Fetch player profiles via gRPC from user-service
        // TODO: Initialize PlayerState objects from player profiles

        BattleState battleState = new BattleState();
        battleState.setBattleId(battleId);
        battleState.setActivePlayerId(player1Id);
        // TODO: Set up player states and initial HP, etc.

        battleStates.put(battleId, battleState);
        return battleState;
    }

    // Submit an action for a battle (called by frontend)
    public BattleState submitAction(String battleId, String playerId, String action) {
        BattleState battleState = battleStates.get(battleId);
        if (battleState == null) {
            throw new IllegalArgumentException("Battle not found");
        }

        // TODO: Validate action, apply game logic, update HP, switch turn, etc.
        // For now, just log the action
        battleState.setLastActionLog(playerId + " performed " + action);

        // TODO: If battle is over, persist results via gRPC to user-service

        return battleState;
    }

    // Get current battle state (for WebSocket payloads)
    public BattleState getBattleState(String battleId) {
        return battleStates.get(battleId);
    }
}
