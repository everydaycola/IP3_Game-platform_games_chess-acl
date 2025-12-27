package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.dtos.chess;

import java.util.List;
import java.util.UUID;

public record GameRegisterMessageDto(
        UUID registrationId,
        String frontendUrl,
        String pictureUrl,
        List <AchievementRegisterDto> availableAchievements

) {
    public record AchievementRegisterDto(
            String code,
            String description
    ) {}
}