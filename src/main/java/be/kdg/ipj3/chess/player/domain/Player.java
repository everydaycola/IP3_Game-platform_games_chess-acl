package be.kdg.ipj3.chess.player.domain;

import be.kdg.ipj3.chess.game.domain.GameId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Player {
    PlatformPlayerId platformId;
    @Setter
    ChessPlayerId chessId;
    GameId gameId;
    boolean isPlayerOne;
}
