package com.aspectzxcc.chrono_critters_user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aspectzxcc.chrono_critters_user.model.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {

}
