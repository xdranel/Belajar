package gendhiramona.lombok;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void testCustomer() {

        var customer = new Customer();
        customer.setId("1");
        customer.setName("Gendhi");

        System.out.println(customer.getId());
        System.out.println(customer.getName());
    }

    @Test
    void testCustomerConstructor() {

        var customer = new Customer("ID", "Gendhi");
        Assertions.assertEquals("ID", customer.getId());
        Assertions.assertEquals("Gendhi", customer.getName());
    }

    @Test
    void testEquals() {
        var customer1 = new Customer("ID", "Gendhi 1");
        var customer2 = new Customer("ID", "Gendhi 2");

        Assertions.assertEquals(customer1, customer2);
        Assertions.assertEquals(customer1.hashCode(), customer2.hashCode());
    }
}
