package gendhiramona.jpa;

import gendhiramona.jpa.entity.*;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmbeddedTest {

    @Test
    void embedded() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Name name = new Name();
        name.setTitle("Mr.");
        name.setFirstName("John");
        name.setLastName("Doe");

        Member member = new Member();
        member.setEmail("john@example.com");
        member.setName(name);

        entityManager.persist(member);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embeddedId() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        DepartmentId departmentId = new DepartmentId();
        departmentId.setCompanyId("abc");
        departmentId.setDepartmentId("tech");

        Department department = new Department();
        department.setId(departmentId);
        department.setName("Technology");

        entityManager.persist(department);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void embeddedIdFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        DepartmentId departmentId = new DepartmentId();
        departmentId.setCompanyId("abc");
        departmentId.setDepartmentId("tech");

        Department department = entityManager.find(Department.class, departmentId);
        Assertions.assertEquals("Technology", department.getName());

        entityTransaction.commit();
        entityManager.close();
    }

}
