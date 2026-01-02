package be.kdg.ipj3.chess.infrastructure.chess;

import be.kdg.ipj3.chess.config.GameRegisterProperties;
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
    private final GameRegisterProperties gameRegisterProperties;

    public ExternalChessApiCatalog(@Qualifier("chessGameUrl") RestClient restClient, GameRegisterProperties gameRegisterProperties) {
        this.restClient = restClient;
        this.gameRegisterProperties = gameRegisterProperties;
    }

    // this method asks the chess game to send a message with its registration info
    @Override
    public boolean registerGameOrThrow() throws ResourceAccessException {
        log.info("Asking chess to publish it's registration info");
        try {
            final var response = restClient.post()
                    // I don't know what this UUID does (•_•)
                    .uri("/platform/register/" + gameRegisterProperties.getRegisterId())
                    .body(new ChessStatusBody(gameRegisterProperties.getInternalUrl()))
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
            log.warn("Unexpected response while asking chess to publish registration info : {} | {}", response.status(), response.message());
        } catch (HttpStatusCodeException e) {
            log.warn("Http error while asking chess to publish registration info : {} | {}", e.getStatusCode(), e.getResponseBodyAsString());
        }
        return false;
    }

    private record ChessStatusResponse(String status, String message, UUID data) {
    }

    private record ChessStatusBody(String frontendUrl) {
    }
}
