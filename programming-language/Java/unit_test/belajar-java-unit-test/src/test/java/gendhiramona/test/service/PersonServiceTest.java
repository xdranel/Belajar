package gendhiramona.test.service;


import gendhiramona.test.data.Person;
import gendhiramona.test.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService(personRepository);
    }

    @Test
    void testGetPersonNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personService.get("not found");
        });
    }

    @Test
    void testGetPersonSuccess() {
        Mockito.when(personRepository.selectById("john"))
                .thenReturn(new Person("001", "John"));

        var person = personService.get("john");

        Assertions.assertNotNull(person);

        Assertions.assertEquals("001", person.getId());
        Assertions.assertEquals("John", person.getName());
    }

    @Test
    void testRegisterSuccess() {
        var person = personService.register("John");
        Assertions.assertNotNull(person);
        Assertions.assertEquals("John", person.getName());
        Assertions.assertNotNull(person.getId());

        Mockito.verify(personRepository, Mockito.times(1))
                .insert(new Person(person.getId(), "John"));
    }
}
