package com.aspectzxcc.chrono_critters_user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aspectzxcc.chrono_critters_user.model.Critter;

public interface CritterRepository extends MongoRepository<Critter, String> {

}
