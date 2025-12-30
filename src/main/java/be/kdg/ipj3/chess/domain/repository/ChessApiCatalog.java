package be.kdg.ipj3.chess.domain.repository;

import org.springframework.web.client.ResourceAccessException;

public interface ChessApiCatalog {
    boolean registerGameOrThrow() throws ResourceAccessException;
}
