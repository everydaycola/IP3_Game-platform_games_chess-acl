package be.kdg.ipj3.chess.game.api;

import be.kdg.ipj3.chess.game.api.dto.GameCreatedDto;
import be.kdg.ipj3.chess.game.api.dto.NewGameDto;
import be.kdg.ipj3.chess.game.api.dto.NewGameRequestDto;
import be.kdg.ipj3.chess.game.application.GameService;
import be.kdg.ipj3.chess.player.domain.PlatformPlayerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chess-acl/api/matches")
public class GameController {

    private final GameService gameService;

    @PostMapping()
    public ResponseEntity<GameCreatedDto> registerGame(@AuthenticationPrincipal Jwt token, @RequestBody NewGameRequestDto newGameRequestDto){
        log.info("Starting a new game");
        newGameRequestDto.validate(PlatformPlayerId.fromToken(token).id());
        final var playerId = new PlatformPlayerId(newGameRequestDto.player1Id());
        final var state = gameService.registerGame(
                playerId,
                newGameRequestDto.player2Id() != null ? new PlatformPlayerId(newGameRequestDto.player2Id()) : playerId
        );
        return ResponseEntity.ok(state);
    }
}
