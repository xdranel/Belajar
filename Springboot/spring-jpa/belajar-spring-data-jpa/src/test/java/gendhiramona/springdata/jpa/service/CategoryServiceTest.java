package gendhiramona.springdata.jpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testSuccess() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.create();
        });
    }

    @Test
    void testFail() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.test();
        });
    }

    @Test
    void testProgrammatic() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.createCategories();
        });
    }

    @Test
    void testManual() {
        assertThrows(RuntimeException.class, () -> {
            categoryService.manual();
        });
    }
}