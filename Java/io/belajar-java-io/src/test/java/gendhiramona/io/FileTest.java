package gendhiramona.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileTest {

    @Test
    void createFile() {
        File file = new File("test.txt");

        Assertions.assertEquals("test.txt", file.getName());
        Assertions.assertFalse(file.exists());
    }

    @Test
    void createFileExist() {
        File file = new File("src/main/resources/application.properties");

        Assertions.assertEquals("application.properties", file.getName());
        Assertions.assertTrue(file.exists());
    }
}
