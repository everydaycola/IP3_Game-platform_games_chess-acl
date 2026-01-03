package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.platform;

import java.util.UUID;

public record AchievementUnlockedMessage(UUID userId, UUID achievementId) {
}