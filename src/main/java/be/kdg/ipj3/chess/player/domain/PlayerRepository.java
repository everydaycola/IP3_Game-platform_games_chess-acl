package be.kdg.ipj3.chess.player.domain;

import be.kdg.ipj3.chess.game.domain.GameId;

import java.util.Optional;

public interface PlayerRepository {
    void save(Player player);
    // see 'note on uniqueness' in JpaPlayerEntity
    // ChessId is unique but can be null, so we expect 1 or 0 players
    Optional<Player> getByChessIdAndGameId(ChessPlayerId chessPlayerId, GameId gameId);
    // we expect 1 or 0 players (there can only be 1 player 1 per game...)
    Optional<Player> getByGameIdAndColor(GameId gameId, boolean isPlayerOne);
}
