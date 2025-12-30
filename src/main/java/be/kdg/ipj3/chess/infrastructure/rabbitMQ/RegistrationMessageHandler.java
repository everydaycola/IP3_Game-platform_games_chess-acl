package be.kdg.ipj3.chess.infrastructure.rabbitMQ;

import be.kdg.ipj3.chess.config.rabbitMQ.RabbitMQProperties;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.GameRegisterChessMessageDto;
import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.RegisterGameMessage;
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
    private final RabbitMQProperties properties;
    private final UrlChecker urlChecker;
    private final TaskScheduler taskScheduler;

    @RabbitListener(queues = "${spring.rabbitmq.fourteengames.register-game-queue-chess}")
    void onGameRegistration(GameRegisterChessMessageDto message){
        log.info("Received message: {}", message);

        final var futureRef = new AtomicReference<ScheduledFuture<?>>();

        final var future = taskScheduler.scheduleWithFixedDelay(() -> {
            try {
                if (!urlChecker.isUrlReachable(properties.getInternalGameUrl())) {
                    log.warn("Chess game not registered yet; url not reachable internally: {}, external is {}", properties.getInternalGameUrl(), properties.getExternalGameUrl());
                    return;
                }

                final var registerGameMessage = RegisterGameMessage.of(message, properties.getExternalGameUrl());

                rabbitTemplate.convertAndSend(
                        properties.getExchangeName(),
                        properties.getRegisterGameBinding(),
                        registerGameMessage
                );
                log.info("Startup game message sent to RabbitMQ: {}", registerGameMessage);

                final var f = futureRef.get();
                if (f != null) f.cancel(false);

            } catch (AmqpException e) {
                // Don’t kill the scheduler thread; just log and let it retry on next tick.
                log.error("Error while trying to register startup game (will retry)", e);
            }
        }, Duration.ofSeconds(5));

        futureRef.set(future);
    }
}
