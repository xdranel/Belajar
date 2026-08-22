package gendhiramona.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManipulationTest {

    @Test
    void fileManipulation() {
        Path path = Path.of("testing.txt");

        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
                Assertions.assertTrue(Files.exists(path));
            } else {
                Files.deleteIfExists(path);
                Assertions.assertFalse(Files.exists(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void directoryManipulation() {
        Path path1 = Path.of("testing");
        Path path2 = Path.of("test.txt");

        Path resultFile = null;

        try {
            if (!Files.exists(path1) || !Files.isDirectory(path1)) {
                Files.createDirectory(path1);
                Assertions.assertTrue(Files.exists(path1));
                Assertions.assertTrue(Files.isDirectory(path1));

                if (!Files.exists(path2)) {
                    Files.createFile(path2);
                }

//                resultFile = Files.move(path2, path1.resolve(path2.getFileName()));
//                Assertions.assertTrue(Files.exists(resultFile));
//                Assertions.assertTrue(Files.isRegularFile(resultFile));
//                Assertions.assertFalse(Files.exists(path2));
            } else {
                if (Files.exists(path2)) {
                    Files.delete(path2);
                    Assertions.assertFalse(Files.exists(path2));
                }

                try {
                    Files.delete(path1);
                    Assertions.assertFalse(Files.exists(path1));
                } catch (IOException e) {
                    System.err.println("Directory not empty, trying to clean it up");

                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(path1)) {
                        for (Path entry : stream) {
                            Files.delete(entry);
                        }
                    }
                    Files.delete(path1);
                    Assertions.assertFalse(Files.exists(path1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Moved File: " + resultFile);
    }
}
