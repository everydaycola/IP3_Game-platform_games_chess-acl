package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.chess;

import java.util.UUID;

public record GameEndedMessageDto(
        UUID gameId,
        UUID whitePlayerId,
        UUID blackPlayerId,
        String whitePlayerName,
        String blackPlayerName,
        String finalFen,
        String endReason,
        String winner,
        int totalMoves,
        String messageType,
        String timestamp
) {
}
