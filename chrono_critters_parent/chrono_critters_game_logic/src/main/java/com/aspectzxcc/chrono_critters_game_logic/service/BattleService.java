package com.aspectzxcc.chrono_critters_game_logic.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aspectzxcc.chrono_critters_game_logic.model.Ability;
import com.aspectzxcc.chrono_critters_game_logic.model.BattleState;
import com.aspectzxcc.chrono_critters_game_logic.model.CritterState;
import com.aspectzxcc.chrono_critters_game_logic.model.CurrentStats;
import com.aspectzxcc.chrono_critters_game_logic.model.PlayerState;
import com.aspectzxcc.chrono_critters_game_logic.model.enums.CritterType;
import com.aspectzxcc.chrono_critters_proto.user.UserServiceGrpc;
import com.aspectzxcc.chrono_critters_proto.user.UserServiceProto.PlayerRequest;
import com.aspectzxcc.chrono_critters_proto.user.UserServiceProto.PlayerResponse;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
@RequiredArgsConstructor
public class BattleService {
    public final Map<String, BattleState> battleStates = new ConcurrentHashMap<>();

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    // Create a new battle (called by lobby service)
    public BattleState createBattle(String battleId, String player1Id, String player2Id) {
        PlayerResponse p1Profile = userServiceStub.getPlayer(
            PlayerRequest.newBuilder().setId(player1Id).build()
        );
        PlayerResponse p2Profile = userServiceStub.getPlayer(
            PlayerRequest.newBuilder().setId(player2Id).build()
        );

        PlayerState playerOneState = mapToPlayerState(p1Profile);
        PlayerState playerTwoState = mapToPlayerState(p2Profile);

        BattleState battleState = new BattleState(battleId, player1Id, playerOneState, playerTwoState, "Battle started!");

        battleStates.put(battleId, battleState);
        return battleState;
    }

    // Submit an action for a battle (called by frontend)
    public BattleState submitAction(String battleId, String playerId, String action) {
        BattleState battleState = battleStates.get(battleId);
        if (battleState == null) {
            throw new IllegalArgumentException("Battle not found");
        }

        // Determine which player is acting and which is defending
        PlayerState activePlayer, opponentPlayer;
        if (battleState.getActivePlayerId().equals(battleState.getPlayerOneState().getPlayerId())) {
            activePlayer = battleState.getPlayerOneState();
            opponentPlayer = battleState.getPlayerTwoState();
        } else {
            activePlayer = battleState.getPlayerTwoState();
            opponentPlayer = battleState.getPlayerOneState();
        }

        CritterState attacker = activePlayer.getTeam().get(activePlayer.getActiveCritterIndex());
        CritterState defender = opponentPlayer.getTeam().get(opponentPlayer.getActiveCritterIndex());

        String log = "";

        // Validate and apply action
        switch (action.toLowerCase()) {
            case "attack" -> {
                int damage = Math.max(1, attacker.getBaseStats().getAttack() - defender.getBaseStats().getDefense());
                int newHp = Math.max(0, defender.getBaseStats().getCurrHealth() - damage);
                defender.getBaseStats().setCurrHealth(newHp);
                log = String.format("%s's %s attacked %s's %s for %d damage!",
                        activePlayer.getUsername(), attacker.getName(),
                        opponentPlayer.getUsername(), defender.getName(), damage);
                if (newHp == 0) {
                    log += String.format(" %s's %s was defeated!", opponentPlayer.getUsername(), defender.getName());
                    // Optionally switch to next critter or mark player defeated
                }
            }
            case "defend" -> {
                // Simple defend: increase defense for next turn
                defender.getBaseStats().setDefense(defender.getBaseStats().getDefense() + 2);
                log = String.format("%s's %s defended and increased defense!",
                        activePlayer.getUsername(), defender.getName());
            }
            default -> throw new IllegalArgumentException("Invalid action");
        }

        // Switch turn
        battleState.setActivePlayerId(opponentPlayer.getPlayerId());
        battleState.setLastActionLog(log);

        // Optionally check for win condition and set isDefeated

        return battleState;
    }

    // Get current battle state (for WebSocket payloads)
    public BattleState getBattleState(String battleId) {
        return battleStates.get(battleId);
    }

    private PlayerState mapToPlayerState(PlayerResponse response) {
        List<CritterState> team = response.getCrittersList().stream()
            .map(protoCritter -> {
                CritterType type = CritterType.valueOf(protoCritter.getType().name());

                // Map baseStats
                CurrentStats baseStats = null;
                if (protoCritter.hasBaseStats()) {
                    baseStats = new CurrentStats(
                        protoCritter.getBaseStats().getMaxHealth(),
                        protoCritter.getBaseStats().getMaxHealth(),
                        protoCritter.getBaseStats().getAttack(),
                        protoCritter.getBaseStats().getDefense()
                    );
                }

                // Map abilities
                List<Ability> abilities = protoCritter.getAbilitiesList().stream()
                    .map(protoAbility -> new Ability(
                        protoAbility.getName(),
                        protoAbility.getPower(),
                        com.aspectzxcc.chrono_critters_game_logic.model.enums.AbilityType.valueOf(protoAbility.getType().name())
                    ))
                    .collect(Collectors.toList());

                return new CritterState(
                    protoCritter.getId(),
                    protoCritter.getName(),
                    type,
                    baseStats,
                    abilities
                );
            })
            .collect(Collectors.toList());

        return new PlayerState(
            response.getId(),
            response.getUsername(),
            team,
            0,         // activeCritterIndex
            "",        // lastAction
            false      // isDefeated
        );
    }
}
