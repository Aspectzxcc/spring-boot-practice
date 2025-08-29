package com.aspectzxcc.chrono_critters_game_logic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleState {
    private String battleId;
    private String activePlayerId;
    private PlayerState playerOneState;
    private PlayerState playerTwoState;
    private String lastActionLog;
}
