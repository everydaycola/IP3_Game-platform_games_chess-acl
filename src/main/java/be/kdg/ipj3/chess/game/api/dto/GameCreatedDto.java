package be.kdg.ipj3.chess.game.api.dto;

import java.util.UUID;

public record GameCreatedDto(
        UUID id,
        UUID whitePlayerId,
        UUID blackPlayerId
) {
}
