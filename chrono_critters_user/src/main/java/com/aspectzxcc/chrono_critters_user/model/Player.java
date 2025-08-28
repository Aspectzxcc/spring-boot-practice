package com.aspectzxcc.chrono_critters_user.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "players")
public class Player {
    private String id;
    private String username;
    private Stats stats;
    private List<Critter> critterRoster;
}