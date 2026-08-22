package gendhiramona.resillience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CircuitBrakerTest {

    public void callMe() {
        log.info("Trying to call me");
        throw new IllegalArgumentException("Error");
    }

    @Test
    void circuitBraker() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("test");

        for (int i = 0; i < 200; i++) {
            try {
                Runnable runnable = CircuitBreaker.decorateRunnable(circuitBreaker, () -> callMe());
                runnable.run();
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
            }
        }
    }

    @Test
    void circuitBrakerConfig() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .failureRateThreshold(20f)
                .slidingWindowSize(10)
                .minimumNumberOfCalls(10)
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("test", config);

        for (int i = 0; i < 200; i++) {
            try {
                Runnable runnable = CircuitBreaker.decorateRunnable(circuitBreaker, () -> callMe());
                runnable.run();
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
            }
        }
    }

    @Test
    void circuitBrakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .failureRateThreshold(20f)
                .slidingWindowSize(10)
                .minimumNumberOfCalls(10)
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
        registry.addConfiguration("config", config);

        CircuitBreaker circuitBreaker = registry.circuitBreaker("test", "config");

        for (int i = 0; i < 200; i++) {
            try {
                Runnable runnable = CircuitBreaker.decorateRunnable(circuitBreaker, () -> callMe());
                runnable.run();
            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
            }
        }
    }
}
