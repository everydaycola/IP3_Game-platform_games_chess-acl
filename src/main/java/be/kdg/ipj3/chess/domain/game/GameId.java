package be.kdg.ipj3.chess.domain.game;

import be.kdg.ipj3.chess.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@Slf4j
public record GameId(UUID id) {
    public NotFoundException notFound() {
        log.error("Match with id {} not found", id);
        return new NotFoundException("Match [" + id + "] not found");
    }
}
