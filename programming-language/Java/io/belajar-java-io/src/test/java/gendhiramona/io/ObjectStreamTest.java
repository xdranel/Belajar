package gendhiramona.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectStreamTest {

    @Test
    void savePerson() {
        Person person = new Person();
        person.setId("1");
        person.setName("Gendhi");

        Path path = Path.of("gendhi.person");

        try (OutputStream stream = Files.newOutputStream(path);
             ObjectOutputStream objectStream = new ObjectOutputStream(stream)) {

            objectStream.writeObject(person);
            objectStream.flush();

        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void getPerson() {
        Path path = Path.of("gendhi.person");

        try (InputStream stream = Files.newInputStream(path);
             ObjectInputStream objectStream = new ObjectInputStream(stream)) {

            Person person = (Person) objectStream.readObject();

            Assertions.assertEquals("1", person.getId());
            Assertions.assertEquals("Gendhi", person.getName());

        } catch (IOException | ClassNotFoundException e) {
            Assertions.fail(e);
        }
    }
}
