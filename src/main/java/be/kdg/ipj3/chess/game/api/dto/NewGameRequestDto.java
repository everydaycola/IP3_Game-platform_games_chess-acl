package be.kdg.ipj3.chess.game.api.dto;

import java.util.UUID;

public record NewGameRequestDto(UUID player1Id, UUID player2Id) {
    public void validate(UUID currentPlayer) {
        if (!currentPlayer.equals(player1Id) && !currentPlayer.equals(player2Id))
            throw new IllegalArgumentException("You cannot start a game without yourself");
    }
}