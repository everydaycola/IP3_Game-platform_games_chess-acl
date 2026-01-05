package be.kdg.ipj3.chess.achievement.infrastructure.messages;

import java.util.UUID;

public record AchievementUnlockedMessage(UUID userId, UUID achievementId) {
}