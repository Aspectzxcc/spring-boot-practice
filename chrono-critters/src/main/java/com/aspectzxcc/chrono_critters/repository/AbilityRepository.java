package com.aspectzxcc.chrono_critters.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aspectzxcc.chrono_critters.model.Ability;

public interface AbilityRepository extends MongoRepository<Ability, String> {

}
