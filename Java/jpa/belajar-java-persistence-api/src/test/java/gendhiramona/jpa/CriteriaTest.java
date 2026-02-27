package gendhiramona.jpa;

import gendhiramona.jpa.entity.Brand;
import gendhiramona.jpa.entity.Product;
import gendhiramona.jpa.entity.SimpleBrand;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CriteriaTest {

    @Test
    void criteriaQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b); // select b from Brand b

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        brands.forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryNonEntity() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Brand> b = criteria.from(Brand.class);
        // criteria.multiselect(b.get("id"), b.get("name")); // select b.id, b.name from Brand b
        criteria.select(builder.array(b.get("id"), b.get("name"))); // select b.id, b.name from Brand b

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            System.out.println("Id : " + object[0]);
            System.out.println("Name : " + object[1]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaQueryConstructor() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<SimpleBrand> criteria = builder.createQuery(SimpleBrand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(builder.construct(SimpleBrand.class, b.get("id"), b.get("name")));
        // select new gendhiramona.jpa.entity.SimpleBrand(b.id, b.name) from Brand b

        TypedQuery<SimpleBrand> query = entityManager.createQuery(criteria);
        List<SimpleBrand> simpleBrands = query.getResultList();
        simpleBrands.forEach(simpleBrand -> System.out.println(simpleBrand.getId() + " : " + simpleBrand.getName()));


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaWhereClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b); // select b from Brand b

        criteria.where(
                builder.equal(b.get("name"), "Xiaomi"),
                builder.isNotNull(b.get("createdAt"))
        );
        // select b from Brand b where b.name = 'Xiaomi' and b.createdAt is not null

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        brands.forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaWhereClauseUsingOrOperator() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);
        criteria.select(b); // select b from Brand b

        criteria.where(
                builder.or(
                        builder.equal(b.get("name"), "Xiaomi"),
                        builder.equal(b.get("name"), "Samsung")
                )
        );
        // select b from Brand b where b.name = 'Xiaomi' or b.name = 'Samsung'

        TypedQuery<Brand> query = entityManager.createQuery(criteria);
        List<Brand> brands = query.getResultList();
        brands.forEach(brand -> System.out.println(brand.getId() + " : " + brand.getName()));


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaJoinClause() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");
        // p.fetch("brand"); // select p from Product p left join fetch p.brand b

        // select p from Product p join p.brand b
        criteria.select(p);
        criteria.where(
                builder.equal(b.get("name"), "Samsung")
        ); // select p from Product p join p.brand b where b.name = 'Samsung'

        TypedQuery<Product> query = entityManager.createQuery(criteria);
        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaNamedParameter() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");

        ParameterExpression<String> brandParameter = builder.parameter(String.class, "brand");

        // select p from Product p join p.brand b
        criteria.select(p);
        criteria.where(
                builder.equal(b.get("name"), brandParameter)
        ); // select p from Product p join p.brand b where b.name = 'Samsung'

        TypedQuery<Product> query = entityManager.createQuery(criteria);
        query.setParameter(brandParameter, "Samsung");

        List<Product> products = query.getResultList();
        for (Product product : products) {
            System.out.println(product.getId() + " : " + product.getName());
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaAggregateQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
        Root<Product> p = criteria.from(Product.class);
        Join<Product, Brand> b = p.join("brand");

        criteria.select(builder.array(
                b.get("id"),
                builder.min(p.get("price")),
                builder.max(p.get("price")),
                builder.avg(p.get("price"))
        )); // select b.id, min(p.price), max(p.price), avg(p.price) from Product p join p.brand b

        criteria.groupBy(b.get("id"));
        // group by b.id

        criteria.having(builder.gt(builder.min(p.get("price")), 500_000L));
        // having min(p.price) > 500_000

        TypedQuery<Object[]> query = entityManager.createQuery(criteria);
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            System.out.println("Brand: " + object[0]);
            System.out.println("Min: " + object[1]);
            System.out.println("Max: " + object[2]);
            System.out.println("Avg: " + object[3]);
        }

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void criteriaNonQuery() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Brand> criteria = builder.createCriteriaUpdate(Brand.class);
        Root<Brand> b = criteria.from(Brand.class);

        criteria.set(b.get("name"), "Apple Updated");
        criteria.set(b.get("description"), "Apple Company");

        criteria.where(
                builder.equal(b.get("id"), "apple")
        );
        // update Brand b set b.name = 'Apple Updated', b.description = 'Apple Company' where b.id = 'apple'

        Query query = entityManager.createQuery(criteria);
        int impactedRecords = query.executeUpdate();
        System.out.println("Successfully updated " + impactedRecords + " records");

        entityTransaction.commit();
        entityManager.close();
    }
}
