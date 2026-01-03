package be.kdg.ipj3.chess.api.dto;

import java.util.UUID;

public record GameCreatedDto(
        UUID gameId,
        UUID whitePlayerId,
        UUID blackPlayerId
) {
}
