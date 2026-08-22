package gendhiramona.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ClosableTest {

    @Test
    void closeIO() {
        Path path = Path.of("pom.xml");
        InputStream inputStream = null;

        try {
            inputStream = Files.newInputStream(path);
            // do something with the stream
        } catch (IOException e) {
            Assertions.fail(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Assertions.fail(e);
                }
            }
        }
    }

    @Test
    void closeIOWithTryResource() {
        Path path = Path.of("pom.xml");

        try (InputStream inputStream1 = Files.newInputStream(path);
             InputStream inputStream2 = Files.newInputStream(path);) {
            // do something with the stream
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }
}
