package gendhiramona.jpa;

import gendhiramona.jpa.entity.*;
import gendhiramona.jpa.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class InheritanceTest {

    @Test
    void singleTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Employee employee = new Employee();
        employee.setId("rina");
        employee.setName("Rina Wati");
        entityManager.persist(employee);

        Manager manager = new Manager();
        manager.setId("joko");
        manager.setName("Joko Doro");
        manager.setTotalEmployee(10);
        entityManager.persist(manager);

        VicePresident vp = new VicePresident();
        vp.setId("Budi");
        vp.setName("Budi Nugraha");
        vp.setTotalManager(5);
        entityManager.persist(vp);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void singleTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Manager manager = entityManager.find(Manager.class, "joko");
        Assertions.assertEquals("Joko Doro", manager.getName());

        Employee employee = entityManager.find(Employee.class, "budi");
        VicePresident vicePresident = (VicePresident) employee;
        Assertions.assertEquals("Budi Nugraha", vicePresident.getName());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGoPay paymentGoPay = new PaymentGoPay();
        paymentGoPay.setId("gopay1");
        paymentGoPay.setAmount(100000L);
        paymentGoPay.setGopayId("089999999999");
        entityManager.persist(paymentGoPay);

        PaymentCreditCard paymentCreditCard = new PaymentCreditCard();
        paymentCreditCard.setId("cc1");
        paymentCreditCard.setAmount(500000L);
        paymentCreditCard.setMaskedCard("4555-5555");
        paymentCreditCard.setBank("BCA");
        entityManager.persist(paymentCreditCard);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFind() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PaymentGoPay paymentGoPay = entityManager.find(PaymentGoPay.class, "gopay1");
        PaymentCreditCard paymentCreditCard = entityManager.find(PaymentCreditCard.class, "cc1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void joinedTableFindParent() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Payment gopay = entityManager.find(Payment.class, "gopay1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassInsert() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = new Transaction();
        transaction.setId("trx1");
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setBalance(1_000_000L);
        entityManager.persist(transaction);

        TransactionDebit debit = new TransactionDebit();
        debit.setId("trx2");
        debit.setCreatedAt(LocalDateTime.now());
        debit.setBalance(2_000_000L);
        debit.setDebitAmount(1_000_000L);
        entityManager.persist(debit);

        TransactionCredit credit = new TransactionCredit();
        credit.setId("trx3");
        credit.setCreatedAt(LocalDateTime.now());
        credit.setBalance(1_000_000L);
        credit.setCreditAmount(1_000_000L);
        entityManager.persist(credit);

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassFindChild() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionDebit debit = entityManager.find(TransactionDebit.class, "trx2");

        TransactionCredit credit = entityManager.find(TransactionCredit.class, "trx3");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void tablePerClassFindParent() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Transaction transaction = entityManager.find(Transaction.class, "trx1");

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void mappedSuperClass() {
        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Brand brand = new Brand();
        brand.setId("xiaomi");
        brand.setName("Xiaomi");
        brand.setDescription("Xiaomi Global");
        brand.setCreatedAt(LocalDateTime.now());
        brand.setUpdatedAt(LocalDateTime.now());
        entityManager.persist(brand);

        entityTransaction.commit();
        entityManager.close();
    }
}
