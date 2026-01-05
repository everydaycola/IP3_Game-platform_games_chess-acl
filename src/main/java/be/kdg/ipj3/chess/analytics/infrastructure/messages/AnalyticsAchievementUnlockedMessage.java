package be.kdg.ipj3.chess.analytics.infrastructure.messages;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record AnalyticsAchievementUnlockedMessage(
        String event_type,
        String timestamp,
        UUID player_id,
        UUID achievement_id,
        String achievement_name,
        String achievement_category,
        String game_name
) implements EventMessage {
    public AnalyticsAchievementUnlockedMessage(UUID playerId, UUID id, String title) {
        this(
                "achievement_unlocked",
                LocalDateTime.now().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT),
                playerId,
                id,
                title,
                "skill",
                "chess"
        );
    }
}
