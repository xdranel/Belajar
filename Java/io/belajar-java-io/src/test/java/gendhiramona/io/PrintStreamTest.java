package gendhiramona.io;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrintStreamTest {

    @Test
    void console() {
        PrintStream stream = System.out;
        stream.println("Hello World!");
        stream.println("Hello World!");
        stream.println("Hello World!");
    }

    @Test
    void printStream() {
        Path path = Path.of("print.txt");

        try (OutputStream stream = Files.newOutputStream(path);
             PrintStream printStream = new PrintStream(stream)) {

            printStream.println("Hello World!");
            printStream.println("Hello World!");
            printStream.println("Hello World!");
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }
}
