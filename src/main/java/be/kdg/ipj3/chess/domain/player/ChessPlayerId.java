package be.kdg.ipj3.chess.domain.player;

import be.kdg.ipj3.chess.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@Slf4j
public record ChessPlayerId(UUID id) {
    public NotFoundException notFound() {
        log.error("Player with id {} not found", id);
        return new NotFoundException("Player [" + id + "] not found");
    }
}
