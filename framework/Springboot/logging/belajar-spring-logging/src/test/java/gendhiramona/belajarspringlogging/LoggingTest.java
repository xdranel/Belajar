package gendhiramona.belajarspringlogging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@Slf4j
@SpringBootTest
@TestPropertySources({
        @TestPropertySource("classpath:application-test.properties")
})
public class LoggingTest {

    @Test
    void testLog() {
        log.info("Belajar Java");
        log.warn("Belajar Spring Boot");
        log.error("Belajar Spring Logging");
    }

    @Test
    void testLongLogging() {
        for (int i = 0; i < 100; i++) {
            log.warn("Hello World {}", i);
        }
    }
}
