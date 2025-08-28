package com.aspectzxcc.chrono_critters.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseStats {
    private int maxHealth;
    private int attack;
    private int defense;
}
