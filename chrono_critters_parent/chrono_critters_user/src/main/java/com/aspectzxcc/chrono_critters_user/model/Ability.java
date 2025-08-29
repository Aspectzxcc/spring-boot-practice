package com.aspectzxcc.chrono_critters_user.model;

import com.aspectzxcc.chrono_critters_user.model.enums.AbilityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ability {
    private String name;
    private int power;
    private AbilityType type;
}
