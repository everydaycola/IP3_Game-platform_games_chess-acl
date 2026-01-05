package be.kdg.ipj3.chess.player.infrastructure;

import be.kdg.ipj3.chess.game.domain.GameId;
import be.kdg.ipj3.chess.player.domain.ChessPlayerId;
import be.kdg.ipj3.chess.player.domain.Player;
import be.kdg.ipj3.chess.player.domain.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DbPlayerRepository implements PlayerRepository {
    private final JpaPlayerRepository jpaPlayerRepository;

    @Override
    public void save(Player player) {
        jpaPlayerRepository
                .save(JpaPlayerEntity.fromDomain(player));
    }

    @Override
    public Optional<Player> getByChessIdAndGameId(ChessPlayerId chessPlayerId, GameId gameId) {
        return jpaPlayerRepository
                .findFirstByChessIdAndGameId(chessPlayerId.id(), gameId.id())
                .map(JpaPlayerEntity::toDomain);
    }

    @Override
    public Optional<Player> getByGameIdAndColor(GameId gameId, boolean isPlayerOne) {
        return jpaPlayerRepository
                .findFirstByGameIdAndIsPlayerOne(gameId.id(), isPlayerOne)
                .map(JpaPlayerEntity::toDomain);
    }
}
