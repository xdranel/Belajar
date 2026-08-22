package gendhiramona.io;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderTest {

    @Test
    void read() {
        Path path = Path.of("output.txt");
        try (Reader reader = Files.newBufferedReader(path)) {
            StringBuilder builder = new StringBuilder();
            int chara;
            int count = 0;
            while ((chara = reader.read()) != -1) {
                builder.append((char) chara);
                count++;
            }
            System.out.println(builder.toString());
            System.out.println(count);
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void readChar() {
        Path path = Path.of("output.txt");
        try (Reader reader = Files.newBufferedReader(path)) {
            StringBuilder builder = new StringBuilder();
//            char[] chars = new char[1024];
            char[] chars = new char[2048];

            int length;
            int count = 0;
            while ((length = reader.read(chars)) != -1) {
                builder.append(chars, 0, length);
                count++;
            }
            System.out.println(builder.toString());
            System.out.println(count);
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }
}
