package gendhiramona.jpa.entity;

import gendhiramona.jpa.listener.UpdatedAtListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter @Setter
@EntityListeners({
        UpdatedAtListener.class
})
@NamedQueries({
        @NamedQuery(name = "Brand.findAll", query = "select b from Brand b"),
        @NamedQuery(name = "Brand.findAllByName", query = "select b from Brand b where b.name = :name")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Brand.native.findAll", query = "select * from brands", resultClass = Brand.class)
})
public class Brand extends AuditableEntity<String> implements UpdatedAtAware {

    private String name;

    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    @Version
    private Long version;
}
