package be.kdg.ipj3.chess.infrastructure.rabbitMQ;

import be.kdg.ipj3.chess.config.rabbitMQ.RabbitMQProperties;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.AchievementUnlockedChessMessage;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.AchievementUnlockedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AchievementMessageHandler {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    @RabbitListener(queues = "${spring.rabbitmq.fourteengames.unlock-achievement-binding}")
    void onUnlockAchievement(AchievementUnlockedChessMessage message) {
        log.info("Received message: {}", message);

        final var achievement = AchievementUnlockedMessage.of(message);

        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getExchangeName(),
                rabbitMQProperties.getUnlockAchievementBinding(),
                achievement
        );
        log.info("Achievement unlock message sent to RabbitMQ: {}", achievement);

    }

}
