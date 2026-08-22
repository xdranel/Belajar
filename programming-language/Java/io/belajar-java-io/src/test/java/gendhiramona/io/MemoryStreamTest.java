package gendhiramona.io;

import org.junit.jupiter.api.Test;

import java.io.StringWriter;

public class MemoryStreamTest {

    @Test
    void memoryStream() {
        StringWriter writer = new StringWriter();

        for (int i = 0; i < 10; i++) {
            writer.write("Hello World!\n");
        }

        String string = writer.getBuffer().toString();
        System.out.println(string);
    }
}
