package be.kdg.ipj3.chess;

import be.kdg.ipj3.chess.shared.config.GameRegisterProperties;
import be.kdg.ipj3.chess.shared.config.rabbitMQ.RabbitMQProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({RabbitMQProperties.class, GameRegisterProperties.class})
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
