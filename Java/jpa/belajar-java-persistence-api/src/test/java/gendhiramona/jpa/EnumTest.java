package gendhiramona.jpa;

import gendhiramona.jpa.entity.Customer;
import gendhiramona.jpa.entity.CustomerType;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @Test
    void enumTest() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("3");
        customer.setName("Jake");
        customer.setPrimaryEmail("jake@example.com");
        customer.setAge((byte) 20);
        customer.setMarried(false);
        customer.setType(CustomerType.PREMIUM);

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
