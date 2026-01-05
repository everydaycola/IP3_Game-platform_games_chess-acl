package be.kdg.ipj3.chess.game.infrastructure.messages;

import java.util.UUID;

public record GameCreatedMessageDto(
        UUID gameId,
        UUID whitePlayerId,
        String whitePlayerName,
        UUID blackPlayerId,
        String blackPlayerName,
        String currentFen,
        String status,
        String messageType,
        String timestamp
) {
}
