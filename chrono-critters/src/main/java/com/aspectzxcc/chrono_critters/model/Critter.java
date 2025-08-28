package com.aspectzxcc.chrono_critters.model;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "critters")
public class Critter {
    private String id;
    private String name;
    private CritterType type;
    private BaseStats baseStats;
    private List<Ability> abilities;
}

@Data
class BaseStats {
    private int health;
    private int attack;
    private int defense;
    private int speed;
}

enum CritterType {
    FIRE,
    WATER,
    GRASS,
}