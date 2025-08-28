package com.aspectzxcc.chrono_critters.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aspectzxcc.chrono_critters.model.Critter;

public interface CritterRepository extends MongoRepository<Critter, String> {

}
