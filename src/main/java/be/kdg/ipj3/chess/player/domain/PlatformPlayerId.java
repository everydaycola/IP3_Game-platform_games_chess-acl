package be.kdg.ipj3.chess.player.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@Slf4j
public record PlatformPlayerId(UUID id) {
    public static PlatformPlayerId fromToken(Jwt token) {
        return new PlatformPlayerId(UUID.fromString(token.getClaimAsString("sub")));
    }
}
