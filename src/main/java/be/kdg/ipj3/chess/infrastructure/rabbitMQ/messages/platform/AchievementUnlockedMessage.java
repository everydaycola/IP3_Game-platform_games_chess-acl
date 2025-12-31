package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.platform;

import be.kdg.ipj3.chess.domain.achievement.AchievementIdMapper;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.chess.AchievementUnlockedChessMessage;

import java.util.UUID;

public record AchievementUnlockedMessage(UUID userId, UUID achievementId) {
    public static AchievementUnlockedMessage of(AchievementUnlockedChessMessage message) {
        return new AchievementUnlockedMessage(
                message.playerId(),
                AchievementIdMapper.fromCode(message.achievementType())
        );
    }
}