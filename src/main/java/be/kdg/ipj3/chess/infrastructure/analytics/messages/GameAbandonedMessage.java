package be.kdg.ipj3.chess.infrastructure.analytics.messages;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public record GameAbandonedMessage(
        String event_type,
        String timestamp,
        UUID game_id,
        String game_name,
        UUID player_id,
        UUID session_id,
        int session_duration_seconds,
        boolean completed,
        String reason,
        String abandoned_at
) implements EventMessage {
    public static GameAbandonedMessage of(UUID gameId, UUID playerId, String reason) {
        return new GameAbandonedMessage(
                "game_abandoned",
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT),
                gameId,
                "chess",
                playerId,
                gameId,
                (int) LocalDateTime.now().atOffset(ZoneOffset.UTC).until(LocalDateTime.now(), ChronoUnit.SECONDS),
                false,
                reason,
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
        );
    }
}
