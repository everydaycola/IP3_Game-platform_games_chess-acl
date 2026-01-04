package be.kdg.ipj3.chess.api;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    class SuccessFlows {

        @Test
        void registerGameAgainstAiShouldReturn200AndValidJson() throws Exception {
            // arrange
            final var player1Id = "10000000-0000-0000-0000-000000000001";
            final var gameId = "00000000-0000-0000-0000-000000000001";

            // act
            mockMvc.perform(post("/chess-acl/api/matches/local")
                    // arrange
                            .with(jwt()
                                    .jwt(jwt -> jwt
                                            .subject(player1Id)
                                            .claim(StandardClaimNames.GIVEN_NAME, "test_user")
                                            .claim(StandardClaimNames.FAMILY_NAME, "user")
                                            .claim(StandardClaimNames.EMAIL, "test_user@test.be")
                                    )
                            ))
                    // assert
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.gameId").value(gameId))
                    .andExpect(jsonPath("$.whitePlayerId").value(player1Id))
                    .andExpect(jsonPath("$.blackPlayerId").value(player1Id));
        }

        @Test
        void registerGameAgainstPlayerShouldReturn200AndValidJson() throws Exception {
            // arrange
            final var player1Id = "10000000-0000-0000-0000-000000000001";
            final var player2Id = "20000000-0000-0000-0000-000000000001";
            final var gameId = "00000000-0000-0000-0000-000000000001";

            // act
            mockMvc.perform(post("/chess-acl/api/matches/online")
                    // arrange
                            .contentType("application/json")
                            .content("{\"opponentId\": \"" + player2Id + "\"}")
                            .with(jwt()
                                    .jwt(jwt -> jwt
                                            .subject(player1Id)
                                            .claim(StandardClaimNames.GIVEN_NAME, "test_user")
                                            .claim(StandardClaimNames.FAMILY_NAME, "user")
                                            .claim(StandardClaimNames.EMAIL, "test_user@test.be")
                                    )
                            ))
                    // assert
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.gameId").value(gameId))
                    .andExpect(jsonPath("$.whitePlayerId").value(player1Id))
                    .andExpect(jsonPath("$.blackPlayerId").value(player2Id));
        }
    }

    @Nested
    class SecurityFlows {

        @Test
        void registerGameAgainstAiUnauthorizedFails() throws Exception {
            // act
            mockMvc.perform(post("/chess-acl/api/matches/local"))
                    // assert
                    .andExpect(status().isUnauthorized());
        }

        @Test
        void registerGameAgainstPlayerUnauthorizedFails() throws Exception {
            // act
            mockMvc.perform(post("/chess-acl/api/matches/online")
                    // arrange
                            .contentType("application/json")
                            .content("{\"opponentId\": \"" + UUID.randomUUID() + "\"}"))
                    // assert
                    .andExpect(status().isUnauthorized());
        }
    }
}