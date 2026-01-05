package be.kdg.ipj3.chess.analytics.infrastructure.messages;

public interface EventMessage {
    String event_type();
    String timestamp();
}
