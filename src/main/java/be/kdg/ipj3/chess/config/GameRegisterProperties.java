package be.kdg.ipj3.chess.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.game.register")
public class GameRegisterProperties {
    private final String internalUrl;
    private final String externalUrl;
    private final String registerId;

    private final String name;
    private final String description;
    private final String genres;
    private final Double price;

}
