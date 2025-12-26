package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

import java.util.UUID;

public record AchievementMessageDto(UUID userId, UUID achievementId) {
}
