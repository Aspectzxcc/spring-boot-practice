package com.aspectzxcc.chrono_critters.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "abilities")
public class Ability {
    private String id;
    private String name;
    private String description;
    private int power;
    private int cost;
}
