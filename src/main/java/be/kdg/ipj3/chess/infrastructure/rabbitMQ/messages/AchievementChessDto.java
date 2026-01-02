package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

public record AchievementChessDto(
        String code,
        String description
) {}