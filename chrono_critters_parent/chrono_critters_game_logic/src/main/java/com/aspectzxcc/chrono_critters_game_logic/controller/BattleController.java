package com.aspectzxcc.chrono_critters_game_logic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspectzxcc.chrono_critters_game_logic.model.BattleState;
import com.aspectzxcc.chrono_critters_game_logic.service.BattleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    // Lobby service creates a new battle
    @PostMapping("/internal/battle")
    public ResponseEntity<BattleState> createBattle(
            @RequestParam String battleId,
            @RequestParam String player1Id,
            @RequestParam String player2Id) {
        BattleState state = battleService.createBattle(battleId, player1Id, player2Id);
        return ResponseEntity.ok(state);
    }

    // Frontend submits a player's turn
    @PostMapping("/battle/{id}/action")
    public ResponseEntity<BattleState> submitAction(
            @PathVariable("id") String battleId,
            @RequestParam String playerId,
            @RequestParam String action) {
        BattleState state = battleService.submitAction(battleId, playerId, action);
        return ResponseEntity.ok(state);
    }
}