package be.kdg.ipj3.chess.infrastructure.rabbitMQ.handlers;

import be.kdg.ipj3.chess.domain.exception.NotFoundException;
import be.kdg.ipj3.chess.domain.game.GameId;
import be.kdg.ipj3.chess.domain.player.ChessPlayerId;
import be.kdg.ipj3.chess.domain.repository.PlayerRepository;
import be.kdg.ipj3.chess.infrastructure.chess.dto.GameCreatedMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameStartMessageHandler {

    private final PlayerRepository playerRepository;

    @RabbitListener(queues = "${spring.rabbitmq.fourteengames.game-created-queue-chess}")
    void onGameCreated(GameCreatedMessageDto message) {

        log.info("Received message: {}", message);

        final var gameId = new GameId(message.gameId());
        final var player1Id = new ChessPlayerId(message.whitePlayerId());
        final var player2Id = new ChessPlayerId(message.blackPlayerId());

        try {
            final var player1 = playerRepository.getByGameIdAndColor(gameId, true).orElseThrow(gameId::notFound);
            player1.setChessId(player1Id);
            playerRepository.save(player1);
        } catch (NotFoundException e) {
            log.error("No player 1 found for for gameId {}: {}", gameId.id(), e.getMessage(), e);
        }

        try {
            final var player2 = playerRepository.getByGameIdAndColor(gameId, false).orElseThrow(gameId::notFound);
            player2.setChessId(player2Id);
            playerRepository.save(player2);
        } catch (NotFoundException e) {
            log.warn("No player 2 found for gameId (not a problem if player 2 is AI) {}: {}", gameId.id(), e.getMessage(), e);
        }
    }
}
