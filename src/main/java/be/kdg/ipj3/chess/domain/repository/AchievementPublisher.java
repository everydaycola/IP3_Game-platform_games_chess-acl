package be.kdg.ipj3.chess.domain.repository;


import be.kdg.ipj3.chess.domain.achievements.Achievement;

import java.util.UUID;

public interface AchievementPublisher {
  public void unlock(UUID playerId, Achievement achievement);
}