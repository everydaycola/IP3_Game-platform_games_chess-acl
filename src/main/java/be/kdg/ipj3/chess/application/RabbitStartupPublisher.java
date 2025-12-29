package be.kdg.ipj3.chess.application;

import be.kdg.ipj3.chess.domain.repository.ChessApiCatalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@Profile("!test")
@RequiredArgsConstructor
public class RabbitStartupPublisher {

    private final TaskScheduler taskScheduler;
    private final ChessApiCatalog chessApiCatalog;

    @EventListener(ApplicationReadyEvent.class)
    public void publishStartupEvent() {

        final var futureRef = new AtomicReference<ScheduledFuture<?>>();

        final var future = taskScheduler.scheduleWithFixedDelay(() -> {
            if (!chessApiCatalog.registerGame()){
                log.error("Error while registering startup game");
                return;
            }
            log.info("Startup message request sent");

            final var f = futureRef.get();
            if (f != null) f.cancel(false);
        }, Duration.ofSeconds(5));

        futureRef.set(future);
    }
}