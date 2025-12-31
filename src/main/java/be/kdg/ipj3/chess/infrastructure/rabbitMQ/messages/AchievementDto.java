package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

import be.kdg.ipj3.chess.domain.achievement.AchievementIdMapper;

import java.util.Locale;
import java.util.UUID;

public record AchievementDto(UUID id, String name, String description) {
    public static AchievementDto of(AchievementChessDto achievement) {
        return new AchievementDto(
                AchievementIdMapper.fromCode(achievement.code()),
                achievement.code().toLowerCase(Locale.ROOT).replace("_", " "),
                achievement.description()
        );
    }
}
