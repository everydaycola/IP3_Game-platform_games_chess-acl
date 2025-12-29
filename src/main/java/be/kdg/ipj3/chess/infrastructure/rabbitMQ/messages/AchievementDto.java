package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

import java.util.UUID;

public record AchievementDto(UUID id, String name, String description) {
}
