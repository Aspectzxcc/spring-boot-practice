package com.aspectzxcc.chrono_critters.model;

import java.util.List;
import lombok.Data;

@Data
public class Player {
    private String id;
    private String username;
    private Stats stats;
    private List<Critter> critterRoster;
}

@Data
class Stats {
    private int wins;
    private int losses;
}