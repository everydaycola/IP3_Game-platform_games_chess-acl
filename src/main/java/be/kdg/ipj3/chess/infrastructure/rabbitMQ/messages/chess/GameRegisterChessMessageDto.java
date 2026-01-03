package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.chess;

import java.util.List;
import java.util.UUID;

public record GameRegisterChessMessageDto(
        UUID registrationId,
        String frontendUrl,
        String pictureUrl,
        List <AchievementChessDto> availableAchievements
) {}