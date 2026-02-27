package gendhiramona.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Transaction {

    @Id
    private String id;

    private Long balance;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
