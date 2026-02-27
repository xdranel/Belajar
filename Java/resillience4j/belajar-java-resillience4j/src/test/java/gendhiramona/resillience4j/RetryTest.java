package gendhiramona.resillience4j;

import io.github.resilience4j.retry.Retry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Slf4j
public class RetryTest {

    void callMe() {
        log.info("Trying to call me");
        throw new IllegalArgumentException("Error");
    }

    @Test
    void testCreateRetry() {
        Retry retry = Retry.ofDefaults("test");

        Runnable runnable = Retry.decorateRunnable(retry, () -> callMe());
        runnable.run();
    }

    String hello() {
        log.info("Trying to say hello");
        throw new IllegalArgumentException("Error");
    }

    @Test
    void testCreteRetrySupplier() {
        Retry retry = Retry.ofDefaults("test");

        Supplier<String> supplier = Retry.decorateSupplier(retry, () -> hello());
        supplier.get();
    }

}
