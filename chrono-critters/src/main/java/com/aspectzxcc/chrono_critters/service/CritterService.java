package com.aspectzxcc.chrono_critters.service;

import org.springframework.stereotype.Service;

import com.aspectzxcc.chrono_critters.repository.CritterRepository;
import com.aspectzxcc.chrono_critters.model.Critter;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CritterService {
	private final CritterRepository critterRepository;
    
	public Critter createCritter(Critter critter) {
		return critterRepository.save(critter);
	}

	public List<Critter> getAllCritters() {
		return critterRepository.findAll();
	}
    
	public Optional<Critter> getCritterById(String id) {
		return critterRepository.findById(id);
	}
    
	public void deleteCritter(String id) {
		critterRepository.deleteById(id);
	}
}
