package com.aspectzxcc.chrono_critters_game_logic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerState {
    private String playerId;
    private String username;
    private List<CritterState> team;
    private int activeCritterIndex;
    private String lastAction;
    private boolean isDefeated;
}
