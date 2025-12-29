package be.kdg.ipj3.chess.config.rabbitMQ;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ChessGameConfig {
    @Bean("chessGameUrl")
    RestClient externalAiCatalogTemplate(@Value("${chessGameUrl.url}") final String url) {
        return RestClient.create(url);
    }
}
