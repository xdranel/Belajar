package gendhiramona.springdata.jpa.repository;

import gendhiramona.springdata.jpa.entity.Category;
import gendhiramona.springdata.jpa.entity.Product;
import gendhiramona.springdata.jpa.model.ProductPrice;
import gendhiramona.springdata.jpa.model.SimpleProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.support.TransactionOperations;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void testCreateProducts() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        {
            Product product = new Product();
            product.setName("MacBook Pro");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("iPhone 13");
            product.setPrice(15_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

    }

    @Test
    void testFindByCategoryName() {
//        productRepository.findAllByCategory_Name("Electronics").forEach(System.out::println);
        List<Product> products = productRepository.findAllByCategory_Name("Electronics");
        assertEquals(2, products.size());
        assertEquals("MacBook Pro", products.get(0).getName());
        assertEquals("iPhone 13", products.get(1).getName());
    }

    @Test
    void testSort() {
//        Sort sort = Sort.by(Sort.Direction.DESC, "price");
//        List<Product> products = productRepository.findAll(sort);
//        assertEquals(2, products.size());
//        assertEquals("iPhone 13", products.get(0).getName());
//        assertEquals("MacBook Pro", products.get(1).getName());

        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("Electronics", sort);
        assertEquals(2, products.size());
        assertEquals("iPhone 13", products.get(0).getName());
        assertEquals("MacBook Pro", products.get(1).getName());
    }

//    @Test
//    void testPageable() {
//        // Page 0, 1 item, sort by id descending
//        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));

    /// /        productRepository.findAllByCategory_Name("Electronics", pageable).forEach(System.out::println);
//        List<Product> products = productRepository.findAllByCategory_Name("Electronics", pageable);
//        assertEquals(1, products.size());
//        assertEquals("iPhone 13", products.get(0).getName());
//
//        // Page 1, 1 item, sort by id descending
//        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
//        products = productRepository.findAllByCategory_Name("Electronics", pageable);
//        assertEquals(1, products.size());
//        assertEquals("MacBook Pro", products.get(0).getName());
//    }
    @Test
    void testPageable() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("Electronics", pageable);

        // To get the total number of items
        assertEquals(1, products.getContent().size());
        // To get which page we are on
        assertEquals(0, products.getNumber());
        // To get total data
        assertEquals(2, products.getTotalElements());
        // To get total pages
        assertEquals(2, products.getTotalPages());
        assertEquals("iPhone 13", products.getContent().get(0).getName());

        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("Electronics", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(1, products.getNumber());
        assertEquals(2, products.getTotalElements());
        assertEquals(2, products.getTotalPages());
        assertEquals("MacBook Pro", products.getContent().get(0).getName());
    }

    @Test
    void testCount() {
        Long count = productRepository.count();
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("Electronics");
        assertEquals(2L, count);
    }

    @Test
    void testExists() {
        boolean exists = productRepository.existsByName("iPhone 13");
        assertTrue(exists);

        exists = productRepository.existsByName("iPhone 14");
        assertFalse(exists);
    }

    @Test
    void testDeleteOld() {
        transactionOperations.executeWithoutResult(transactionStatus -> { // transaction 1
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);

            Product product = new Product();
            product.setName("Samsung Galaxy S22");
            product.setPrice(15_000_000L);
            product.setCategory(category);
            productRepository.save(product); // transaction 1

            int delete = productRepository.deleteByName("Samsung Galaxy S22"); // transaction 1
            assertEquals(1, delete);

            delete = productRepository.deleteByName("Samsung Galaxy S22"); // transaction 1
            assertEquals(0, delete);
        });
    }

    @Test
    void testDeleteNew() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        Product product = new Product();
        product.setName("Samsung Galaxy S22");
        product.setPrice(15_000_000L);
        product.setCategory(category);
        productRepository.save(product); // transaction 1

        int delete = productRepository.deleteByName("Samsung Galaxy S22"); // transaction 2
        assertEquals(1, delete);

        delete = productRepository.deleteByName("Samsung Galaxy S22"); // transaction 3
        assertEquals(0, delete);
    }

    @Test
    void testNamedQuery() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Product> products = productRepository.searchProductUsingName("MacBook Pro", pageable);
        assertEquals(1, products.size());
        assertEquals("MacBook Pro", products.get(0).getName());
    }

    @Test
    void testSearchProducts() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
//        productRepository.searchProduct("MacBook Pro").forEach(System.out::println);
        Page<Product> products = productRepository.searchProduct("%MacBook Pro%", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(0, products.getNumber());
        assertEquals(1, products.getTotalPages());
        assertEquals(1, products.getTotalElements());

        products = productRepository.searchProduct("%Electronics%", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(0, products.getNumber());
        assertEquals(2, products.getTotalPages());
        assertEquals(2, products.getTotalElements());
    }

    @Test
    void testModifying() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            int total = productRepository.deleteProductUsingName("Wrong");
            assertEquals(0, total);

            total = productRepository.updateProductPriceToZero(1L);
            assertEquals(1, total);

            Product product = productRepository.findById(1L).orElse(null);
            assertNotNull(product);
            assertEquals(0, product.getPrice().longValue());
        });
    }

    @Test
    void testStream() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);

            Stream<Product> stream = productRepository.streamAllByCategory(category);
//        stream.forEach(System.out::println);
            stream.forEach(product -> System.out.println(product.getId() + " : " + product.getName()));
        });
    }

    @Test
    void testSlice() {
        Pageable firstPage = PageRequest.of(0, 1);

        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        Slice<Product> slice = productRepository.findAllByCategory(category, firstPage);
//        slice.forEach(product -> System.out.println(product.getId() + " : " + product.getName()));

        while (slice.hasNext()) {
            slice.getContent().forEach(product -> System.out.println(product.getId() + " : " + product.getName()));

            slice = productRepository.findAllByCategory(category, slice.nextPageable());

            slice.forEach(product -> System.out.println(product.getId() + " : " + product.getName()));

        }
    }

    @Test
    void testLock1() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try {
                Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
                assertNotNull(product);
                product.setPrice(30_000_000L);

                Thread.sleep(20_000L);
                productRepository.save(product);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testLock2() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
            assertNotNull(product);
            product.setPrice(10_000_000L);
            productRepository.save(product);
        });
    }

    @Test
    void testSpecification() {
        // Use specification if the query not a simple "Equals"
        // but needed where clause
        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaQuery.where(
                    criteriaBuilder.or(
                            criteriaBuilder.equal(root.get("name"), "MacBook Pro"),
                            criteriaBuilder.equal(root.get("name"), "iPhone 14")
                    )
            ).getRestriction();
        };
        List<Product> products = productRepository.findAll(specification);
        assertEquals(2, products.size());
    }

    @Test
    void testProjection() {
        // Projection
//        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%iPhone%");

        // Dynamic Projection
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%iPhone%", SimpleProduct.class);
        assertEquals(1, simpleProducts.size());

        List<ProductPrice> productPrices = productRepository.findAllByNameLike("%iPhone%", ProductPrice.class);
        assertEquals(1, productPrices.size());
    }
}