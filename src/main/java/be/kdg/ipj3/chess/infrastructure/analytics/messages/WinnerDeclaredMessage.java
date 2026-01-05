package be.kdg.ipj3.chess.infrastructure.analytics.messages;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record WinnerDeclaredMessage(
        String event_type,
        String timestamp,
        UUID game_id,
        UUID session_id,
        String winner,
        UUID winner_id,
        String game_name
) implements EventMessage {
    public WinnerDeclaredMessage(UUID game_id, UUID session_id, String winner, UUID winner_id) {
        this(
                "winner_declared",
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT),
                game_id,
                session_id,
                winner,
                winner_id,
                "chess"
        );
    }
}
