package gendhiramona.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Payment {

    @Id
    private String id;

    private Long amount;
}
