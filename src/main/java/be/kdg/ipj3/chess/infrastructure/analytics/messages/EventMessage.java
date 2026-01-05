package be.kdg.ipj3.chess.infrastructure.analytics.messages;

public interface EventMessage {
    String event_type();
    String timestamp();
}
