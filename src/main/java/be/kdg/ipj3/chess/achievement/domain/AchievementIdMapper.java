package be.kdg.ipj3.chess.achievement.domain;

import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@UtilityClass // a useful lombok annotation for a static utility class
public class AchievementIdMapper {
    public static UUID fromCode(String achievementCode) {
        // Chess works with unique name codes like `PAWN_STORM` as id's
        // this would always convert `PAWN_STORM` to the same UUID
        return UUID.nameUUIDFromBytes(achievementCode.getBytes(StandardCharsets.UTF_8));
    }
}
