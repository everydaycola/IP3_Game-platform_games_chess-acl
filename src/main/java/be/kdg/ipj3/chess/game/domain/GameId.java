package be.kdg.ipj3.chess.game.domain;

import be.kdg.ipj3.chess.shared.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public record GameId(UUID id) {
    public NotFoundException notFound() {
        log.error("Match with id {} not found", id);
        return new NotFoundException("Match [" + id + "] not found");
    }
}
