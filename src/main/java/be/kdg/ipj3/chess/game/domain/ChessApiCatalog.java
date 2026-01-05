package be.kdg.ipj3.chess.game.domain;

import org.springframework.web.client.ResourceAccessException;

public interface ChessApiCatalog {
    boolean registerGameOrThrow() throws ResourceAccessException;
}
