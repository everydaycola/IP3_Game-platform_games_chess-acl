package be.kdg.ipj3.chess.game.api;

import be.kdg.ipj3.chess.game.api.dto.GameCreatedDto;
import be.kdg.ipj3.chess.game.api.dto.NewGameDto;
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

    @PostMapping("/local")
    public ResponseEntity<GameCreatedDto> registerGameAgainstYourself(@AuthenticationPrincipal Jwt token){
        log.info("Starting a new game");
        final var player1Id = PlatformPlayerId.fromToken(token);
        final var state = gameService.registerGame(player1Id, null);
        return ResponseEntity.ok(state);
    }

    @PostMapping("/online")
    public ResponseEntity<GameCreatedDto> registerGameAgainstPlayer(@AuthenticationPrincipal Jwt token, @RequestBody NewGameDto newMatchDto){
        log.info("Starting a new game");
        final var player1Id = PlatformPlayerId.fromToken(token);
        final var player2Id = new PlatformPlayerId(newMatchDto.opponentId());
        final var state = gameService.registerGame(player1Id,player2Id);
        return ResponseEntity.ok(state);
    }
}
