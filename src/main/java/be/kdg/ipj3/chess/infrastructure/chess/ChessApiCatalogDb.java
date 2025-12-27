package be.kdg.ipj3.chess.infrastructure.chess;

import be.kdg.ipj3.chess.domain.repository.ChessApiCatalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Slf4j @Component public class ChessApiCatalogDb implements ChessApiCatalog {

    private final RestClient restClient;

    public ChessApiCatalogDb(@Qualifier("ChessGameUrl") RestClient restClient) {
        this.restClient = restClient;
    }

    // this method asks the chess game to send a message with its registration info
    @Override public boolean registerGame() {
        log.info("Asking the Ai to make a move");
        try {
            final var response = restClient.post()
                                           // todo I don't know what this UUID does
                                           .uri("/platform/register/", UUID.randomUUID())
                                           .retrieve()
                                           .body(ChessStatusRespomnse.class);

            if (response != null && response.status().equals("ok")) {
                log.info(response.message());
                return true;
            }

            if (response == null) {
                log.error("No response from chess api");
                return false;
            }

            log.error("Error while registering game: {}", response.message());
        } catch (final HttpStatusCodeException | ResourceAccessException e) {
            log.error("Error while asking AI for a move: {}", e.getMessage());
        }
        return false;
    }

    private record ChessStatusRespomnse(String status, String message, UUID data) {}
}
