package com.aspectzxcc.chrono_critters.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aspectzxcc.chrono_critters.model.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {

}
