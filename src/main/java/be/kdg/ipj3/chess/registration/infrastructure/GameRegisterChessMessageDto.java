package be.kdg.ipj3.chess.registration.infrastructure;

import be.kdg.ipj3.chess.achievement.infrastructure.messages.AchievementChessDto;

import java.util.List;
import java.util.UUID;

public record GameRegisterChessMessageDto(
        UUID registrationId,
        String frontendUrl,
        String pictureUrl,
        List <AchievementChessDto> availableAchievements
) {}