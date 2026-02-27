package gendhiramona.jpa;

import gendhiramona.jpa.entity.*;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JpaQueryLanguageTest {

    @Test
    void select() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b", Brand.class);
        List<Brand> brandList = query.getResultList();

        for (Brand brand : brandList) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void whereClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Member> query = entityManager.createQuery("select m from Member m where " +
                "m.name.firstName = :firstName and m.name.lastName = :lastName", Member.class);
        query.setParameter("firstName", "John");
        query.setParameter("lastName", "Doe");

        // query.getResultList().forEach(member -> System.out.println(member.getId() + " : " + member.getFullName()));

        List<Member> memberList = query.getResultList();
        // memberList.forEach(member -> System.out.println(member.getId() + " : " + member.getFullName()));

        for (Member member : memberList) {
            System.out.println(member.getId() + " : " + member.getFullName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Product> query = entityManager.createQuery("select p from Product p join p.brand b where b.name = :brand", Product.class);
        // Fetching the brand object in the same query (eager fetch)
        // TypedQuery<Product> query = entityManager.createQuery("select p from Product p join fetch p.brand b where b.name = :brand", Product.class);
        query.setParameter("brand", "Samsung");

        List<Product> productList = query.getResultList();
        productList.forEach(product -> System.out.println(product.getId() + " : " + product.getName() + ", " + product.getBrand().getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinFetchClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.likes p where p.name = :productName", User.class);
        query.setParameter("productName", "Samsung Galaxy S2");

        // query.getResultList().forEach(user -> System.out.println("User: " + user.getName() + ", " + user.getLikes().size()));
        List<User> users = query.getResultList();
        for (User user : users) {
            System.out.println("User: " + user.getName());
            for (Product product : user.getLikes()) {
                System.out.println("\tProduct: " + product.getName());
            }
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void orderClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.name desc ", Brand.class);
        List<Brand> brands = query.getResultList();

        for (Brand brand : brands) {
            System.out.println(brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void insertRandomBrand() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        for (int i = 0; i < 100; i++) {
            Brand brand = new Brand();
            brand.setId(String.valueOf(i));
            brand.setName("Brand " + i);
            entityManager.persist(brand);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void limitOffset() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createQuery("select b from Brand b order by b.id", Brand.class);
        query.setFirstResult(10);
        query.setMaxResults(10);

        List<Brand> brands = query.getResultList();
        brands.forEach(brand -> System.out.println(brand.getId()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void namedQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Brand> query = entityManager.createNamedQuery("Brand.findAllByName", Brand.class);
        query.setParameter("name", "Xiaomi");

        // query.getResultList().forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));
        List<Brand> brands = query.getResultList();
        brands.forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectSomeField() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, b.name from Brand b where b.name = :name", Object[].class);
        query.setParameter("name", "Xiaomi");

        List<Object[]> brands = query.getResultList();
        brands.forEach(brand -> System.out.println(brand[0] + " : " + brand[1]));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void selectNewConstructor() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<SimpleBrand> query = entityManager.createQuery("select new gendhiramona.jpa.entity.SimpleBrand(b.id, b.name) " +
                "from Brand b where b.name = :name", SimpleBrand.class);
        query.setParameter("name", "Xiaomi");

        List<SimpleBrand> brands = query.getResultList();
        brands.forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select min(p.price), max(p.price), avg(p.price) from Product p", Object[].class);
        Object[] resultList = query.getSingleResult();

        System.out.println("Min: " + resultList[0]);
        System.out.println("Max: " + resultList[1]);
        System.out.println("Avg: " + resultList[2]);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void aggregateQueryGroupBy() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Object[]> query = entityManager.createQuery("select b.id, min(p.price), max(p.price), avg(p.price) " +
                "from Product p join p.brand b group by b.id having min(p.price) > :min", Object[].class);
        query.setParameter("min", 500_000L);

        List<Object[]> resultList = query.getResultList();
        for (Object[] result : resultList) {
            System.out.println("Brand: " + result[0]);
            System.out.println("Min: " + result[1]);
            System.out.println("Max: " + result[2]);
            System.out.println("Avg: " + result[3]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void nativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createNativeQuery("select * from brands where brands.created_at is not null", Brand.class);
        List<Brand> resultList = query.getResultList();

        for (Brand brand : resultList) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void namedNativeQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createNamedQuery("Brand.native.findAll", Brand.class);
        List<Brand> resultList = query.getResultList();

        for (Brand brand : resultList) {
            System.out.println(brand.getId() + " : " + brand.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void nonQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createQuery("update Brand b set b.name = :name where b.id = :id");
        query.setParameter("name", "Samsung Updated");
        query.setParameter("id", "samsung");
        int impactedRecords = query.executeUpdate();
        System.out.println("Successfully updated " + impactedRecords + " records");

        entityTransaction.commit();
        entityManager.close();
    }
}
