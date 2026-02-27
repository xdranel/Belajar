package gendhiramona.jpa;

import gendhiramona.jpa.entity.Customer;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

public class DataTypeTest {

    @Test
    void dataType() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId("2");
        customer.setName("Jane Doe");
        customer.setPrimaryEmail("jane@example.com");
        customer.setAge((byte) 25);
        customer.setMarried(true);

        entityManager.persist(customer);

        entityTransaction.commit();
        entityManager.close();
    }
}
