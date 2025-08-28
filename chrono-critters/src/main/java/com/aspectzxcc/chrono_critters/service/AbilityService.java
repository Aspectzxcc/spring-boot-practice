package com.aspectzxcc.chrono_critters.service;

import org.springframework.stereotype.Service;

import com.aspectzxcc.chrono_critters.repository.AbilityRepository;
import com.aspectzxcc.chrono_critters.model.Ability;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbilityService {
    private final AbilityRepository abilityRepository;
    
    public Ability createAbility(Ability ability) {
        return abilityRepository.save(ability);
    }

    public List<Ability> getAllAbilities() {
        return abilityRepository.findAll();
    }
    
    public Optional<Ability> getAbilityById(String id) {
        return abilityRepository.findById(id);
    }
    
    public void deleteAbility(String id) {
        abilityRepository.deleteById(id);
    }
}
