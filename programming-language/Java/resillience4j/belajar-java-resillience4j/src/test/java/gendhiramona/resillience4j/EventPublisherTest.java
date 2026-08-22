package gendhiramona.resillience4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

@Slf4j
public class EventPublisherTest {

    private String hello() {
        throw new IllegalArgumentException("Error");
    }

    @Test
    void retry() {
        Retry retry = Retry.ofDefaults("test");
        retry.getEventPublisher().onRetry(event -> {
            log.info("try to Retry");
        });

        try {
            Supplier<String> supplier = Retry.decorateSupplier(retry, () -> hello());
            supplier.get();
        } catch (Exception e) {
            System.out.println(retry.getMetrics().getNumberOfFailedCallsWithRetryAttempt());
            System.out.println(retry.getMetrics().getNumberOfFailedCallsWithoutRetryAttempt());
            System.out.println(retry.getMetrics().getNumberOfSuccessfulCallsWithRetryAttempt());
            System.out.println(retry.getMetrics().getNumberOfSuccessfulCallsWithoutRetryAttempt());
        }
    }

    @Test
    void registry() {
        RetryRegistry registry = RetryRegistry.ofDefaults();
        registry.getEventPublisher().onEntryAdded(event -> {
            log.info("Entry added: {}", event.getAddedEntry().getName());
        });

        registry.retry("test1");
        registry.retry("test1");
        registry.retry("test3");
    }
}
