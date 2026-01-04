package be.kdg.ipj3.chess.infrastructure.analytics.messages;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record GameStartedMessage(
        String event_type,
        String timestamp,
        UUID game_id,
        UUID player_id,
        UUID session_id,
        String game_name,
        int player_count,
        String started_at
) implements EventMessage {
    public GameStartedMessage(UUID game_id, UUID player_id, UUID session_id) {
        this(
                "game_started",
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT),
                game_id,
                player_id,
                session_id,
                "chess",
                2,
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
        );
    }
}
