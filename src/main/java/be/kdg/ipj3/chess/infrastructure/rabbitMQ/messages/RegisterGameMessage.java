package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

import be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages.dtos.registeration.FullGameDto;

public record RegisterGameMessage(FullGameDto gameDto) {
}
