package be.kdg.ipj3.chess.config.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.rabbitmq.fourteengames")
public class RabbitMQProperties {
    private final String exchangeName; // messaging to/from the platform
    private final String registerGameQueue;
    private final String registerGameBinding;
    private final String unlockAchievementBinding;

    private final String exchangeNameChess; // messaging to/from chess
    private final String registerGameQueueChess; // registering the game
    private final String registerGameBindingChess;
    private final String gameCreatedBindingChess; // starting a game
    private final String gameCreatedQueueChess;
    private final String gameEndedBindingChess; // ending a game
    private final String gameEndedQueueChess;
    private final String unlockAchievementBindingChess; // unlocking an achievement
    private final String unlockAchievementQueueChess;

    private final String analyticsExchange;
    private final String analyticsGameStartedBinding;
    private final String analyticsGameEndedBinding;
    private final String analyticsGameAbandonedBinding;
    private final String analyticsSessionStartedBinding;
    private final String analyticsWinnerDeclaredBinding;
    private final String analyticsAchievementUnlockedBinding;
}
