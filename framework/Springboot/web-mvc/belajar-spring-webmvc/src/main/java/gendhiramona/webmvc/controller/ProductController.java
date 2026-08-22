package gendhiramona.webmvc.controller;

import gendhiramona.webmvc.filter.ProductFilter;
import gendhiramona.webmvc.model.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. @RestController means @Controller + @ResponseBody on all methods
// 2. Class-level @RequestMapping sets the base URL for all endpoints in this class
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    // --- @GetMapping & @PathVariable ---
    // Handles GET /api/v1/products/42
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId) {
        Product product = new Product(productId, "Laptop", 999.99);
        return ResponseEntity.ok(product);
    }

    // --- @GetMapping & @RequestParam ---
    // Handles GET /api/v1/products/search?category=electronics&limit=10
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {

        // Logic to search products using query parameters
        return ResponseEntity.ok(List.of());
    }

    // --- @PostMapping & @RequestBody ---
    // Handles POST /api/v1/products with a JSON payload in the request body
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product createDto) {
        // 'createDto' is automatically deserialized from incoming JSON
        return new ResponseEntity<>(createDto, HttpStatus.CREATED);
    }

    // --- @PutMapping & @RequestBody + @PathVariable ---
    // Handles PUT /api/v1/products/42 to replace an existing resource
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product updateDto) {

        return ResponseEntity.ok(updateDto);
    }

    // --- @DeleteMapping ---
    // Handles DELETE /api/v1/products/42
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // --- @ModelAttribute Example ---
    // Handles POST request with multipart/form-data or form-urlencoded parameters
    @PostMapping(value = "/filter", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> filterProducts(@ModelAttribute ProductFilter filter) {
        // Spring automatically binds form fields (e.g., filter.minPrice, filter.maxPrice)
        // to the fields inside the ProductFilter object
        return ResponseEntity.ok("Filtered using: " + filter.getMinPrice());
    }
}