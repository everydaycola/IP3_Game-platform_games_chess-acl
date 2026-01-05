package be.kdg.ipj3.chess.shared.config.rabbitMQ;

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

    //REGISTER GAME CHESS (the queue for receiving the registering message from chess.)
    @Bean Queue registerGameQueueChess(){
        return QueueBuilder.nonDurable(properties.getRegisterGameQueueChess()).build();
    }

    @Bean Binding registerGameBingingChess(){
        return BindingBuilder.bind(registerGameQueueChess()).to(chessExchange()).with(properties.getRegisterGameBindingChess());
    }

    //GAME CREATED CHESS (the queue for receiving the game-created message from chess.)
    @Bean Queue gameCreatedQueueChess(){
        return QueueBuilder.nonDurable(properties.getGameCreatedQueueChess()).build();
    }

    @Bean Binding gameCreatedBindingChess(){
        return BindingBuilder.bind(gameCreatedQueueChess()).to(chessExchange()).with(properties.getGameCreatedBindingChess());
    }

    //UNLOCK ACHIEVEMENT CHESS (the queue for receiving the unlocking message from chess.)
    @Bean Queue unlockAchievementQueueChess(){
        return QueueBuilder.nonDurable(properties.getUnlockAchievementQueueChess()).build();
    }

    @Bean Binding unlockAchievementBindingChess(){
        return BindingBuilder.bind(unlockAchievementQueueChess()).to(chessExchange()).with(properties.getUnlockAchievementBindingChess());
    }

    //GAME END CHESS (the queue for receiving the end-game message from chess.)
    @Bean Queue gameEndQueueChess(){
        return QueueBuilder.nonDurable(properties.getGameEndedQueueChess()).build();
    }

    @Bean Binding gameEndBindingChess(){
        return BindingBuilder.bind(gameEndQueueChess()).to(chessExchange()).with(properties.getGameEndedBindingChess());
    }

}
