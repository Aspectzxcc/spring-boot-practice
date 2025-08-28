package com.aspectzxcc.chrono_critters.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.aspectzxcc.chrono_critters.model.Critter;
import com.aspectzxcc.chrono_critters.model.Player;
import com.aspectzxcc.chrono_critters.service.CritterService;
import com.aspectzxcc.chrono_critters.service.PlayerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PlayerGraphQLController {
    private final PlayerService playerService;
    private final CritterService critterService;

    @QueryMapping
    public List<Player> players() {
        return playerService.getAllPlayers();
    }

    @QueryMapping
    public Player playerById(@Argument String id) {
        return playerService.getPlayerById(id).orElse(null);
    }

    @QueryMapping
    public List<Critter> critters() {
        return critterService.getAllCritters();
    }

    @QueryMapping
    public Critter critterById(@Argument String id) {
        return critterService.getCritterById(id).orElse(null);
    }
}