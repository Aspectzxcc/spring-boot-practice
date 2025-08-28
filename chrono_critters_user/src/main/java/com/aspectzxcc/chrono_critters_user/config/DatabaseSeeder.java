package com.aspectzxcc.chrono_critters_user.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aspectzxcc.chrono_critters_user.model.*;
import com.aspectzxcc.chrono_critters_user.model.enums.*;
import com.aspectzxcc.chrono_critters_user.repository.*;

import java.util.*;

@Configuration
public class DatabaseSeeder {
    @Bean
    CommandLineRunner seedDatabase(PlayerRepository playerRepository, CritterRepository critterRepository) {
        return _ -> {
            // Reset collections to avoid duplicates
            playerRepository.deleteAll();
            critterRepository.deleteAll();

            // Create abilities as subdocuments
            Ability flameBurst = new Ability("Flame Burst", 40, AbilityType.OFFENSIVE);
            Ability waterShield = new Ability("Water Shield", 35, AbilityType.DEFENSIVE);
            Ability leafHeal = new Ability("Leaf Heal", 15, AbilityType.SUPPORT);

            // Seed Critters
            Critter blazion = new Critter("c1", "Blazion", CritterType.FIRE,
                    new BaseStats(100, 30, 20),
                    Arrays.asList(flameBurst));
            Critter aquarion = new Critter("c2", "Aquarion", CritterType.WATER,
                    new BaseStats(110, 25, 25),
                    Arrays.asList(waterShield));
            Critter leafion = new Critter("c3", "Leafion", CritterType.GRASS,
                    new BaseStats(95, 28, 22),
                    Arrays.asList(leafHeal));
            critterRepository.saveAll(Arrays.asList(blazion, aquarion, leafion));

            // Seed Players
            Player ash = new Player("p1", "Ash",
                    new Stats(10, 5),
                    Arrays.asList(blazion, aquarion));
            Player misty = new Player("p2", "Misty",
                    new Stats(8, 7),
                    Arrays.asList(aquarion, leafion));
            playerRepository.saveAll(Arrays.asList(ash, misty));
        };
    }
}