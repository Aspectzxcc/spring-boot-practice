package com.aspectzxcc.chrono_critters.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.aspectzxcc.chrono_critters.model.enums.CritterType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "critters")
public class Critter {
    private String id;
    private String name;
    private CritterType type;
    private BaseStats baseStats;
    private List<Ability> abilities;
}