package be.kdg.ipj3.chess.config.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.rabbitmq.fourteengames")
public class RabbitMQProperties {
    private final String internalGameUrl;
    private final String externalGameUrl;
    private final String exchangeName;
    private final String registerGameQueue;
    private final String registerGameBinding;
    private final String unlockAchievementBinding;

    private final String exchangeNameChess;
    private final String registerGameQueueChess;
    private final String registerGameBindingChess;
}
