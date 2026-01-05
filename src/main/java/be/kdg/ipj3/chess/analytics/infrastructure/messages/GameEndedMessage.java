package be.kdg.ipj3.chess.analytics.infrastructure.messages;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record GameEndedMessage(
        String event_type,
        String timestamp,
        UUID game_id,
        UUID player_id,
        UUID session_id,
        int session_duration,
        boolean completed,
        String ended_at
) implements EventMessage {
    public GameEndedMessage(UUID game_id, UUID player_id, UUID session_id, int session_duration) {
        this(
                "game_ended",
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT),
                game_id,
                player_id,
                session_id,
                session_duration,
                true,
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
    }
}
