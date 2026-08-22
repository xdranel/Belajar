package gendhiramona.jpa.entity;

import gendhiramona.jpa.listener.UpdatedAtListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Calendar;

@Entity
@Table(name = "categories")
@Getter
@Setter
@EntityListeners({
        UpdatedAtListener.class
})
public class Category implements UpdatedAtAware{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Calendar createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
