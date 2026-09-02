package gendhiramona.springdata.jpa.repository;

import gendhiramona.springdata.jpa.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testInsert() {
        Category category = new Category();
        category.setName("Gadgets");

        categoryRepository.save(category);

        assertNotNull(category.getId());
    }

    @Test
    void testUpdate() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        category.setName("Electronics");
        categoryRepository.save(category);
//        categoryRepository.delete(category);
        

        category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);
        assertEquals("Electronics", category.getName());
    }

    @Test
    void testQueryMethod() {
        Category category = categoryRepository.findFirstByNameEquals("Electronics").orElse(null);
        assertNotNull(category);
        assertEquals("Electronics", category.getName());

//        categoryRepository.findAllByNameLike("Electronics").forEach(System.out::println);
        List<Category> categories = categoryRepository.findAllByNameLike("Electronics");
        assertEquals(1, categories.size());
        assertEquals("Electronics", categories.get(0).getName());
    }

    @Test
    void testAudit() {
        Category category = new Category();
        category.setName("Gadgets");
        categoryRepository.save(category);

        assertNotNull(category.getId());
        assertNotNull(category.getCreatedDate());
        assertNotNull(category.getLastModifiedDate());
    }

    @Test
    void testExample1() {
        Category category = new Category();
        category.setName("Electronics");

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);
        assertEquals(1, categories.size());
    }

    @Test
    void testExample2() {
        Category category = new Category();
        category.setName("Electronics");
        category.setId(1L);

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);
        assertEquals(1, categories.size());
    }

    @Test
    void testExampleMatcher() {
        Category category = new Category();
        category.setName("ELECTRONICS");

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues()
                .withIgnoreCase();
        // if the only thing you need is "equals"
        // use Example not Specification
        Example<Category> example = Example.of(category, matcher);

        List<Category> categories = categoryRepository.findAll(example);
        assertEquals(1, categories.size());
    }
}