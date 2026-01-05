package be.kdg.ipj3.chess.player.domain;

import be.kdg.ipj3.chess.shared.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public record ChessPlayerId(UUID id) {
    public NotFoundException notFound() {
        log.error("Player with id {} not found", id);
        return new NotFoundException("Player [" + id + "] not found");
    }
}
