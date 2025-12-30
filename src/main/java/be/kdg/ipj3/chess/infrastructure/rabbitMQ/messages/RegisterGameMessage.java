package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

public record RegisterGameMessage(FullGameDto gameDto) {

    public static RegisterGameMessage of(GameRegisterChessMessageDto gameDto, String frontendUrl) {
        return new RegisterGameMessage(
                new FullGameDto(
                       gameDto.registrationId(),
                       "Chess",
                        "A classic game of strategy and skill.",
                        4.99,
                        gameDto.pictureUrl(),
                        gameDto.pictureUrl(),
                        "Strategy",
                        frontendUrl,
                        gameDto.availableAchievements().stream().map(AchievementDto::of).toList()

                )
        );
    }
}
