package be.kdg.ipj3.chess.infrastructure.chess;

import be.kdg.ipj3.chess.domain.repository.ChessApiCatalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Slf4j
@Component
public class ExternalChessApiCatalog implements ChessApiCatalog {

    private final RestClient restClient;

    public ExternalChessApiCatalog(@Qualifier("chessGameUrl") RestClient restClient) {
        this.restClient = restClient;
    }

    // this method asks the chess game to send a message with its registration info
    @Override
    public boolean registerGame() {
        log.info("Asking chess to publish it's registration info");
        try {
            final var response = restClient.post()
                    // todo I don't know what this UUID does, it has to be the game ID
                    .uri("/platform/register/" + "fc7cc04a-5c7a-44e3-8159-797f01c49d87")
                    .body(new ChessStatusBody("http://localhost:3333/game/fc7cc04a-5c7a-44e3-8159-797f01c49d87"))
                    .retrieve()
                    .body(ChessStatusResponse.class);

            if (response != null && response.status().equals("success")) {
                log.info(response.message());
                return true;
            }

            if (response == null) {
                log.error("No response from chess api");
                return false;
            }

            log.info(response.status());
            log.error("Error while asking chess to publish registration info : {} | {}", response.status(), response.message());
        } catch (final HttpStatusCodeException | ResourceAccessException e) {
            log.error("Unknown error while asking chess to publish registration info : {}", e.getMessage());
        }
        return false;
    }

    private record ChessStatusResponse(String status, String message, UUID data) {
    }

    private record ChessStatusBody(String frontendUrl) {
    }
}
