package gendhiramona.resillience4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@Slf4j
public class RetryRegistryTest {

    void callMe() {
        log.info("Trying to call me");
        throw new IllegalArgumentException("Error");
    }

    @Test
    void testRetryRegistry() {

        RetryRegistry registry = RetryRegistry.ofDefaults();

        Retry retry1 = registry.retry("test1");
        Retry retry2 = registry.retry("test1");

        Assertions.assertEquals(retry1, retry2);
    }

    @Test
    void testRetryRegistryConfig() {

        RetryConfig config = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofSeconds(1))
                .build();

//        RetryRegistry registry = RetryRegistry.of(config);
        RetryRegistry registry = RetryRegistry.ofDefaults();
      registry.addConfiguration("config", config);

        Retry retry1 = registry.retry("test1", "config");
        Retry retry2 = registry.retry("test1", "config");

//        Retry retry1 = registry.retry("test1"); // using default config

        Assertions.assertEquals(retry1, retry2);

        Runnable runnable = Retry.decorateRunnable(retry1, () -> callMe());
        runnable.run();
    }
}
