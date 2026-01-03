package be.kdg.ipj3.chess.domain.repository;

import be.kdg.ipj3.chess.domain.game.GameId;
import be.kdg.ipj3.chess.domain.player.ChessPlayerId;
import be.kdg.ipj3.chess.domain.player.Player;

import java.util.Optional;

public interface PlayerRepository {
    void save(Player player);
    // see 'note on uniqueness' in JpaPlayerEntity
    // ChessId is unique but can be null, so we expect 1 or 0 players
    Optional<Player> getByChessIdAndGameId(ChessPlayerId chessPlayerId, GameId gameId);
    // we expect 1 or 0 players (there can only be 1 player 1 per game...)
    Optional<Player> getByGameIdAndColor(GameId gameId, boolean isPlayerOne);
}
