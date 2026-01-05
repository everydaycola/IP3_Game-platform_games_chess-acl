package be.kdg.ipj3.chess.player.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaPlayerRepository extends JpaRepository<JpaPlayerEntity, UUID> {
    // see 'note on uniqueness' in JpaPlayerEntity
    // ChessId is unique but can be null, so we do 'first'
    Optional<JpaPlayerEntity> findFirstByChessIdAndGameId(UUID chessId, UUID gameId);
    // this combo is unique, so we do 'first'
    Optional<JpaPlayerEntity> findFirstByGameIdAndIsPlayerOne(UUID gameId, boolean isPlayerOne);
}
