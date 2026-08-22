package gendhiramona.jpa;

import gendhiramona.jpa.entity.Customer;
import gendhiramona.jpa.entity.Member;
import gendhiramona.jpa.entity.Name;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionTest {

    @Test
    void create() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr.");
        name.setFirstName("Daniel");
        name.setLastName("Savitar");

        Member member = new Member();
        member.setEmail("test@example.com");
        member.setName(name);

        member.setHobbies(new ArrayList<String>());
        member.getHobbies().add("Sports");
        member.getHobbies().add("Reading");

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void update() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);

        member.getHobbies().add("Music");

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void updateSkills() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Member member = entityManager.find(Member.class, 2);
        member.setSkills(new HashMap<>());
        member.getSkills().put("Java", 90);
        member.getSkills().put("PHP", 80);
        member.getSkills().put("Python", 85);

        entityManager.merge(member);

        entityTransaction.commit();
        entityManager.close();
    }


}
