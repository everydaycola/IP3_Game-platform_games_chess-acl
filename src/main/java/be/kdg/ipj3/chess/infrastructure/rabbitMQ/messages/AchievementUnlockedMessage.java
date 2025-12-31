package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

import be.kdg.ipj3.chess.domain.achievement.AchievementIdMapper;

import java.util.UUID;

public record AchievementUnlockedMessage(UUID userId, UUID achievementId) {
    public static AchievementUnlockedMessage of(AchievementUnlockedChessMessage message) {
        return new AchievementUnlockedMessage(
                message.playerId(),
                AchievementIdMapper.fromCode(message.achievementType())
        );
    }
}