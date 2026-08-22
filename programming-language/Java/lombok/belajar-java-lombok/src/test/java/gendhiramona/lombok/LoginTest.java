package gendhiramona.lombok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest {

    @Test
    void testCreate() {

        var login1 = Login.createEmpty();
        Assertions.assertNull(login1.getUsername());
        Assertions.assertNull(login1.getPassword());

        var login2 = Login.create("Gendhi", "123456");
        Assertions.assertEquals("Gendhi", login2.getUsername());
        Assertions.assertEquals("123456", login2.getPassword());
    }

    @Test
    void testToString() {
        var login = Login.create("Gendhi", "123456");
        System.out.println(login);
    }
}
