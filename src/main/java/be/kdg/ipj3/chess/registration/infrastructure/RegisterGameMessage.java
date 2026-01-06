package be.kdg.ipj3.chess.registration.infrastructure;

import be.kdg.ipj3.chess.achievement.infrastructure.messages.AchievementDto;
import be.kdg.ipj3.chess.game.infrastructure.messages.FullGameDto;
import be.kdg.ipj3.chess.shared.config.GameRegisterProperties;

public record RegisterGameMessage(FullGameDto gameDto) {

    public static RegisterGameMessage of(GameRegisterChessMessageDto gameDto, GameRegisterProperties props) {
        return new RegisterGameMessage(
                new FullGameDto(
                       gameDto.registrationId(),
                       props.getName(),
                        2,
                        props.getAiStartGameEndpoint(),
                        props.getStartGameEndpoint(),
                        props.getDescription(),
                        props.getPrice(),
                        gameDto.pictureUrl(),
                        gameDto.pictureUrl(),
                        props.getGenres(),
                        props.getExternalUrl(),
                        gameDto.availableAchievements().stream().map(AchievementDto::of).toList()

                )
        );
    }
}
