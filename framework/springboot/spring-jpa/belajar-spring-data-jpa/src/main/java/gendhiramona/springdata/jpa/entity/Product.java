package gendhiramona.springdata.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@NamedQueries({
        // NamedQuery does not support Sort, it support Pageable but without Sort
        @NamedQuery(
                name = "Product.searchProductUsingName",
                // you can add the sort here but it's not flexible
                query = "SELECT p FROM Product p WHERE p.name = :name"
        )
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
