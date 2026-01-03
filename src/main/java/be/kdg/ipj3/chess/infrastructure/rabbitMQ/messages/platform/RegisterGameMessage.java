package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.platform;

import be.kdg.ipj3.chess.config.GameRegisterProperties;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.chess.GameRegisterChessMessageDto;

public record RegisterGameMessage(FullGameDto gameDto) {

    public static RegisterGameMessage of(GameRegisterChessMessageDto gameDto, GameRegisterProperties props) {
        return new RegisterGameMessage(
                new FullGameDto(
                       gameDto.registrationId(),
                       props.getName(),
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
