package be.kdg.ipj3.chess.analytics.infrastructure;

import be.kdg.ipj3.chess.analytics.infrastructure.messages.*;
import be.kdg.ipj3.chess.shared.config.rabbitMQ.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyticsMessagePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties properties;

    public void publishAchievementUnlockedMessage(AnalyticsAchievementUnlockedMessage message) {
        publishMessage(message, properties.getAnalyticsAchievementUnlockedBinding());
    }

    public void publishGameAbandonedMessage(GameAbandonedMessage message) {
        // this is not properly recorded by the external chess game, so cannot be set up.
        publishMessage(message, properties.getAnalyticsGameAbandonedBinding());
    }

    public void publishGameEndedMessage(GameEndedMessage message) {
        publishMessage(message, properties.getAnalyticsGameEndedBinding());
    }

    public void publishGameStartedMessage(GameStartedMessage message) {
        publishMessage(message, properties.getAnalyticsGameStartedBinding());
    }

    public void publishSessionStartedMessage(SessionStartedMessage message) {
        publishMessage(message, properties.getAnalyticsSessionStartedBinding());
    }

    public void publishWinnerDeclaredMessage(WinnerDeclaredMessage message) {
        publishMessage(message, properties.getAnalyticsWinnerDeclaredBinding());
    }

    private void publishMessage(EventMessage message, String routingKey) {
        try {
            rabbitTemplate.convertAndSend(
                    properties.getAnalyticsExchange(),
                    routingKey,
                    message
            );
            log.info("Published message: {} to routing key: {} | {}", message.event_type(), routingKey, message);
        } catch (AmqpException e) {
            log.warn("Failed to publish message: {} - RabbitMQ connection error: {} | {}",
                    message.event_type(), e.getMessage(), message);
        }
    }
}

