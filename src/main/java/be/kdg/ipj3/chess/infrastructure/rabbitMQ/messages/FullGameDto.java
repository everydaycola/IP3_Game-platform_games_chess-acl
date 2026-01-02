package be.kdg.ipj3.chess.infrastructure.rabbitMQ.messages;

import java.util.List;
import java.util.UUID;

public record FullGameDto(
        UUID id,
        String name,
        String description,
        double price,
        String image,
        String icon,
        String genre,
        String url,
        List<AchievementDto> achievements
) {


}
