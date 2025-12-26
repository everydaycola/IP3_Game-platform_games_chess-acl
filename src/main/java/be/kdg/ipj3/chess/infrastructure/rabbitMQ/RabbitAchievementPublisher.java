package be.kdg.ipj3.chess.infrastructure.rabbitMQ;

import be.kdg.ipj3.chess.config.rabbitMQ.RabbitMQProperties;
import be.kdg.ipj3.chess.domain.achievements.Achievement;
import be.kdg.ipj3.chess.domain.repository.AchievementPublisher;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.AchievementMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitAchievementPublisher implements AchievementPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties properties;

    // The method now takes the Enum, not a String ID
    @Override
    public void unlock(UUID playerId, Achievement achievement) {
        log.info("Sending achievement unlocked message for player {} and achievement {} : {} ({})", playerId, achievement.getTitle(), achievement.getDescription(), achievement.getId());
        rabbitTemplate.convertAndSend(
                properties.getExchangeName(),
                properties.getUnlockAchievementBinding(),
                new AchievementMessageDto(
                    playerId,
                    achievement.getId()
                )
        );
    }
}