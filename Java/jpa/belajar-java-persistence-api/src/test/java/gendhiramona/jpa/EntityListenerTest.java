package gendhiramona.jpa;

import gendhiramona.jpa.entity.Category;
import gendhiramona.jpa.entity.Member;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class EntityListenerTest {

    @Test
    void listener() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = new Category();
        category.setName("Sports");

        entityManager.persist(category);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateListener() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Category category = entityManager.find(Category.class, 3);

        category.setDescription("Sports Category Updated");

        entityManager.merge(category);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void listenerEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 1);
        Assertions.assertEquals("Mr. John Doe", member.getFullName());

        entityTransaction.commit();
        entityManager.close();
    }
}
