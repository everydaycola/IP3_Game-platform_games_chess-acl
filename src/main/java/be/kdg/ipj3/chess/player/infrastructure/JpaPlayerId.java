package be.kdg.ipj3.chess.player.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JpaPlayerId implements Serializable {
    private UUID gameId;
    private boolean isPlayerOne;
}