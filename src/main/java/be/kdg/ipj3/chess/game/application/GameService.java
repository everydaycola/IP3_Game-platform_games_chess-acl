package be.kdg.ipj3.chess.game.application;

import be.kdg.ipj3.chess.game.api.dto.GameCreatedDto;
import be.kdg.ipj3.chess.game.domain.GameId;
import be.kdg.ipj3.chess.player.domain.PlatformPlayerId;
import be.kdg.ipj3.chess.player.domain.Player;
import be.kdg.ipj3.chess.player.domain.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class GameService {

    private final PlayerRepository playerRepository;

    @Value("${spring.game.register.register-id}")
    private UUID registerId; // fixed id for now


    // this method really only exists so we can get the player's id from the jwt token and save them with the game id.
    //  Currently, the game id is fixed to (uuid) 000...0001
    //  It does not really create the game as, when chess starts, it makes the anyway game, regardless of if it already exists.
    public GameCreatedDto registerGame(PlatformPlayerId player1Id, PlatformPlayerId player2Id) {
        log.info("Registering a new game between {} and {} to the ACL", player1Id, (player2Id == player1Id ? "themself" : player2Id));

        playerRepository.save(new Player(
                player1Id,
                null,
                new GameId(registerId),
                true
        ));
        // if the game is local, the player gets achievements for both sides
        playerRepository.save(new Player(
                player2Id,
                null,
                new GameId(registerId),
                false
        ));

        return new GameCreatedDto(
                registerId,
                player1Id.id(),
                player2Id.id()
        );
    }
}
