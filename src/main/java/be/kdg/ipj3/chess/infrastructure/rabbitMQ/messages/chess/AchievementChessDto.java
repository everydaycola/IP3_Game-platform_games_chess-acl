package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.chess;

public record AchievementChessDto(
        String code,
        String description
) {}