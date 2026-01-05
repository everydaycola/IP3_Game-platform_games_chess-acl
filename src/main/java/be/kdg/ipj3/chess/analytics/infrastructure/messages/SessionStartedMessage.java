package be.kdg.ipj3.chess.analytics.infrastructure.messages;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record SessionStartedMessage(
        String event_type,
        String timestamp,
        UUID player_id,
        UUID session_id,
        UUID game_id,
        String game_name,
        int session_duration_seconds
) implements EventMessage {

    public SessionStartedMessage(UUID session_id, UUID game_id, UUID player_id) {
        this(
                "session_started",
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT),
                player_id,
                session_id,
                game_id,
                "chess",
                0
        );
    }
}
