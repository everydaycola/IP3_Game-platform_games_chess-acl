package be.kdg.ipj3.chess.infrastructure.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationMessageHandler {


    @RabbitListener(queues = "${spring.rabbitmq.fourteengames.register-game-queue-chess}")
    void onGameRegistration(String message){
        log.info("Received message: {}", message);
    }
}
