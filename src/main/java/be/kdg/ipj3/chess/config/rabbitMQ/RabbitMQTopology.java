package be.kdg.ipj3.chess.config.rabbitMQ;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQTopology {

    private final RabbitMQProperties properties;

    @Bean TopicExchange xivExchange() {
        return new TopicExchange(properties.getExchangeName());
    }
    @Bean TopicExchange chessExchange() {return new TopicExchange(properties.getExchangeNameChess());}

    //REGISTER GAME (the queue for sending the translated message to the main platform.)
    @Bean Queue registerGameQueue(){
        return QueueBuilder.nonDurable(properties.getRegisterGameQueue()).build();
    }

    @Bean Binding registerGameBinging(){
        return BindingBuilder.bind(registerGameQueue()).to(xivExchange()).with(properties.getRegisterGameBinding());
    }

    //REGISTER GAME CHESS (the queue for recieving the registering message from chess.)
    @Bean Queue registerGameQueueChess(){
        return QueueBuilder.nonDurable(properties.getRegisterGameQueueChess()).build();
    }

    @Bean Binding registerGameBingingChess(){
        return BindingBuilder.bind(registerGameQueueChess()).to(chessExchange()).with(properties.getRegisterGameBindingChess());
    }
}
