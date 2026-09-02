package gendhiramona.springdata.jpa.repository;

import gendhiramona.springdata.jpa.entity.Category;
import gendhiramona.springdata.jpa.entity.Product;
import gendhiramona.springdata.jpa.model.ProductPrice;
import gendhiramona.springdata.jpa.model.SimpleProduct;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

// use query method if the query itself isn't complex
// use @Query if the query is complex and not like @NamedQuery only support Pageable, @Query support Sort and Pageable
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // Dynamic Projection
    <T> List<T> findAllByNameLike(String name, Class<T> tClass);

//    List<SimpleProduct> findAllByNameLike(String name);
//    // reference to ProductPrice
//    // this is error cause both of them have same name
//    // but for example you want to get SimpleProduct or ProductPrice you can youse Dynamic Projection
//    List<ProductPrice> findAllByNameLike(String name);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    // Using Optional
    Optional<Product> findFirstByIdEquals(Long id);

    // Using slice so that you can get current, next, previous and total pages
    Slice<Product> findAllByCategory(Category category, Pageable pageable);

    // Careful with the return stream
    // it will be closed when the stream is consumed or ended
    // so if for example you wanted to foreach the stream
    // the stream should be inside a transcation by using @Transactional
    Stream<Product> streamAllByCategory(Category category);

    @Modifying
    @Query("delete from Product p where p.name = :name")
    int deleteProductUsingName(@Param("name") String name);
    // you can add @Transactional if you want to rollback the transaction if the query fails
    // instead of making a new transaction.
    @Modifying
    @Query("update Product p set p.price = 0 where p.id = :id")
    int updateProductPriceToZero(@Param("id") Long id);

//    @Query("select p from Product p where p.name like %:name% or p.category.name like %:name%")
    @Query(
            value = "select p from Product p where p.name like :name or p.category.name like :name",
            // if you wanted the return type to be Page<Product> then you can use countQuery
            // so its more flexible than using NamedQuery
            countQuery = "select count(p) from Product p where p.name like :name or p.category.name like :name"
    )
//    List<Product> searchProduct(@Param("name") String name, Pageable pageable);
    Page<Product> searchProduct(@Param("name") String name, Pageable pageable);

    List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);
//    List<Product> searchProductUsingName(@Param("name") String name);

    @Transactional
    int deleteByName(String name);

    boolean existsByName(String name);
    
    Long countByCategory_Name(String categoryName);

//    List<Product> findAllByCategory_Name(String name, Pageable pageable);
    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

    List<Product> findAllByCategory_Name(String name, Sort sort);

    List<Product> findAllByCategory_Name(String name);
}
