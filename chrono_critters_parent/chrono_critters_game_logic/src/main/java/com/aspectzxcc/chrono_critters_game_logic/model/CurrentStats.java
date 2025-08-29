package com.aspectzxcc.chrono_critters_game_logic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentStats {
    private int maxHealth;
    private int currHealth;
    private int attack;
    private int defense;
}
