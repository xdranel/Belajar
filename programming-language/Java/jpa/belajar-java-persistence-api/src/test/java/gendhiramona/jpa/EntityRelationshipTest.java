package gendhiramona.jpa;

import gendhiramona.jpa.entity.*;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class EntityRelationshipTest {

    @Test
    void oneToOnePersist() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Credential credential = new Credential();
        credential.setId("johnd");
        credential.setEmail("johndoe@example.com");
        credential.setPassword("rahasia");
        entityManager.persist(credential);

        User user = new User();
        user.setId("johnd");
        user.setName("John Doe");
        entityManager.persist(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "gendhirp");
        Assertions.assertNotNull(user.getCredential());
        Assertions.assertNotNull(user.getWallet());

        Assertions.assertEquals("gendhirp@example.com", user.getCredential().getEmail());
        Assertions.assertEquals("rahasia", user.getCredential().getPassword());
        Assertions.assertEquals(1_000_000L, user.getWallet().getBalance());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToOneJoinColumn() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "gendhirp");

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(1_000_000L);
        entityManager.persist(wallet);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToManyInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("samsung");
        brand.setName("Samsung");
        brand.setDescription("Samsung brand");
        entityManager.persist(brand);

        Product product1 = new Product();
        product1.setId("sg1");
        product1.setName("Samsung Galaxy S1");
        product1.setBrand(brand);
        product1.setDescription("Samsung Galaxy S1");
        product1.setPrice(1_000_000L);
        entityManager.persist(product1);

        Product product2 = new Product();
        product2.setId("sg2");
        product2.setName("Samsung Galaxy S2");
        product2.setBrand(brand);
        product2.setDescription("Samsung Galaxy S2");
        product2.setPrice(2_000_000L);
        entityManager.persist(product2);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToManyFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "samsung");
        Assertions.assertNotNull(brand.getProducts());
        Assertions.assertEquals(2, brand.getProducts().size());

        brand.getProducts().forEach(product -> System.out.println(product.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void oneToManyUpdate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = entityManager.find(Brand.class, "samsung");

        Product product = brand.getProducts().stream()
                .filter(p -> p.getId().equals("sg2"))
                .findFirst()
                .orElseThrow();

        product.setName("Samsung Galaxy S2");
        product.setDescription("Samsung Galaxy S2");
        product.setPrice(2_000_000L);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void manyToManyInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, "gendhirp");
        user.setLikes(new HashSet<>());

        Product product1 = entityManager.find(Product.class, "sg1");
        Product product2 = entityManager.find(Product.class, "sg2");

        user.getLikes().add(product1);
        user.getLikes().add(product2);

        entityManager.merge(user);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void manyToManyUpdate() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            User user = entityManager.find(User.class, "johnd");
            Product productToRemove = entityManager.find(Product.class, "sg2");

            int sizeBefore = user.getLikes().size();
            user.getLikes().remove(productToRemove);

            entityTransaction.commit();

            entityManager.refresh(user);
            Assertions.assertEquals(sizeBefore - 1, user.getLikes().size());
            Assertions.assertFalse(user.getLikes().contains(productToRemove));

        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }

    @Test
    void fetch() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Product product = entityManager.find(Product.class, "sg1");
        Brand brand = product.getBrand();
        brand.getName();
        Assertions.assertNotNull(brand);
        // Brand brand = entityManager.find(Brand.class, "samsung");


        entityTransaction.commit();
        entityManager.close();
    }
}
