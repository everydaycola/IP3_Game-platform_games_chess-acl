package be.kdg.ipj3.chess.infrastructure.jpa;

import jakarta.persistence.*;
import be.kdg.ipj3.chess.domain.game.GameId;
import be.kdg.ipj3.chess.domain.player.ChessPlayerId;
import be.kdg.ipj3.chess.domain.player.PlatformPlayerId;
import be.kdg.ipj3.chess.domain.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "players")
@IdClass(JpaPlayerId.class) // <--- Links to the composite key class
public class JpaPlayerEntity {

    // Removed the generated UUID id

    @Id // Part 1 of Composite Key
    @Column(nullable = false)
    private UUID gameId;

    @Id // Part 2 of Composite Key
    @Column(nullable = false)
    private boolean isPlayerOne;

    @Column
    private UUID platformId;

    @Column @Setter
    private UUID chessId;

    public static JpaPlayerEntity fromDomain(Player player) {
        return new JpaPlayerEntity(
                player.getGameId() != null ? player.getGameId().id() : null,
                player.isPlayerOne(),
                player.getPlatformId() != null ? player.getPlatformId().id() : null,
                player.getChessId() != null ? player.getChessId().id() : null
        );
    }

    public Player toDomain() {
        return new Player(
                new PlatformPlayerId(this.platformId),
                new ChessPlayerId(this.chessId),
                new GameId(this.gameId),
                this.isPlayerOne
        );
    }
}