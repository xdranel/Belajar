package gendhiramona.resillience4j;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.management.RuntimeErrorException;
import java.time.Duration;
import java.util.function.Supplier;

@Slf4j
public class RetryConfigTest {

    String helloWorld() {
        log.info("Trying to say hello world");
        throw new IllegalArgumentException("Error");
    }

    @Test
    void testRetryConfig() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofSeconds(1))
                .retryExceptions(IllegalArgumentException.class)
                .ignoreExceptions(RuntimeErrorException.class)
                .build();

        Retry retry = Retry.of("test", config);
        Supplier<String> supplier = Retry.decorateSupplier(retry, () -> helloWorld());
        supplier.get();
    }
}
