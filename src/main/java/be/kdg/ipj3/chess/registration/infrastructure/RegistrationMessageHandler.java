package be.kdg.ipj3.chess.registration.infrastructure;

import be.kdg.ipj3.chess.shared.config.GameRegisterProperties;
import be.kdg.ipj3.chess.shared.config.rabbitMQ.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationMessageHandler {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;
    private final GameRegisterProperties gameRegisterProperties;
    private final UrlChecker urlChecker;
    private final TaskScheduler taskScheduler;

    @RabbitListener(queues = "${spring.rabbitmq.fourteengames.register-game-queue-chess}")
    void onGameRegistration(GameRegisterChessMessageDto message){
        log.info("Received message: {}", message);

        final var futureRef = new AtomicReference<ScheduledFuture<?>>();

        final var future = taskScheduler.scheduleWithFixedDelay(() -> {
            try {
                if (!urlChecker.isUrlReachable(gameRegisterProperties.getInternalUrl())) {
                    log.warn("Chess game not registered yet; url not reachable internally: {}, external is {}", gameRegisterProperties.getInternalUrl(), gameRegisterProperties.getExternalUrl());
                    return;
                }

                final var registerGameMessage = RegisterGameMessage.of(message, gameRegisterProperties);

                rabbitTemplate.convertAndSend(
                        rabbitMQProperties.getExchangeName(),
                        rabbitMQProperties.getRegisterGameBinding(),
                        registerGameMessage
                );
                log.info("Startup game message sent to RabbitMQ: {}", registerGameMessage);

                final var f = futureRef.get();
                if (f != null) f.cancel(false);

            } catch (AmqpException e) {
                log.error("Error while trying to register startup game (will retry)", e);
            }
        }, Duration.ofSeconds(5));

        futureRef.set(future);
    }
}
