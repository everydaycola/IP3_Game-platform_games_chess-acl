package be.kdg.ipj3.chess.infrastructure.rabbitMQ.handlers;

import be.kdg.ipj3.chess.config.rabbitMQ.RabbitMQProperties;
import be.kdg.ipj3.chess.domain.achievement.AchievementIdMapper;
import be.kdg.ipj3.chess.domain.exception.NotFoundException;
import be.kdg.ipj3.chess.domain.game.GameId;
import be.kdg.ipj3.chess.domain.player.ChessPlayerId;
import be.kdg.ipj3.chess.domain.repository.PlayerRepository;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.chess.AchievementUnlockedChessMessage;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.platform.AchievementUnlockedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AchievementMessageHandler {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;
    private final PlayerRepository playerRepository;

    // the achievement contains the ID that chess uses, we need the id that we use.
    // so we look for the respective id saved in our DB upon startup.
    @RabbitListener(queues = "${spring.rabbitmq.fourteengames.unlock-achievement-queue-chess}")
    void onUnlockAchievement(AchievementUnlockedChessMessage message) {
        log.info("Received message: {}", message);

        final var playerId = new ChessPlayerId(message.playerId());
        final var gameId = new GameId(message.gameId());

        try {
            final var player = playerRepository.getByChessIdAndGameId(playerId, gameId).orElseThrow(playerId::notFound);

            final var achievement = new AchievementUnlockedMessage(
                    player.getPlatformId().id(),
                    AchievementIdMapper.fromCode(message.achievementType())
            );

            rabbitTemplate.convertAndSend(
                    rabbitMQProperties.getExchangeName(),
                    rabbitMQProperties.getUnlockAchievementBinding(),
                    achievement
            );
            log.info("Achievement unlock message sent to RabbitMQ: {}", achievement);
        } catch (NotFoundException e) {
            log.warn("No player found for chessId {} and gameId {}: {}", playerId.id(), gameId.id(), e.getMessage());
        } catch (AmqpException e) {
            log.error("Error while trying to send achievement unlock message: {}", e.getMessage());
        }

    }

}
