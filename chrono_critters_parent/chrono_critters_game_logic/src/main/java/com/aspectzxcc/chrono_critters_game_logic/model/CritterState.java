package com.aspectzxcc.chrono_critters_game_logic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.aspectzxcc.chrono_critters_game_logic.model.enums.CritterType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CritterState {
    private String id;
    private String name;
    private CritterType type;
    private CurrentStats baseStats;
    private List<Ability> abilities;
}
