package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

import java.util.UUID;

public record AchievementUnlockedChessMessage(
        UUID gameId,
        UUID playerId,
        String playerName,
        String achievementType,
        String achievementDescription,
        String messageType,
        String timestamp
) {}
