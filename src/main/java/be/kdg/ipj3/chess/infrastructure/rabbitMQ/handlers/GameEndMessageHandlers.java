package be.kdg.ipj3.chess.infrastructure.rabbitMQ.handlers;

import be.kdg.ipj3.chess.infrastructure.analytics.AnalyticsMessagePublisher;
import be.kdg.ipj3.chess.infrastructure.analytics.messages.GameEndedMessage;
import be.kdg.ipj3.chess.infrastructure.analytics.messages.WinnerDeclaredMessage;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.chess.GameEndedMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameEndMessageHandlers {

    private final AnalyticsMessagePublisher analyticsMessagePublisher;

    @RabbitListener(queues = "${spring.rabbitmq.fourteengames.game-ended-queue-chess}")
    void onGameCreated(GameEndedMessageDto message) {

        log.info("Received message: {}", message);

        analyticsMessagePublisher.publishGameEndedMessage(
                new GameEndedMessage(
                        message.gameId(),
                        message.whitePlayerId(),
                        message.gameId(),
                        1000
                )
        );

        analyticsMessagePublisher.publishWinnerDeclaredMessage(
                new WinnerDeclaredMessage(
                        message.gameId(),
                        message.gameId(),
                        message.winner(),
                        message.winner().equalsIgnoreCase("white") ?  message.whitePlayerId() : message.blackPlayerId()
                )
        );





    }

}
