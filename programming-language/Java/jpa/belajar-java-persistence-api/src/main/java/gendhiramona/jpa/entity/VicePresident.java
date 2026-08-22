package gendhiramona.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("VP")
@Getter
@Setter
public class VicePresident extends Employee{

    @Column(name = "total_manager")
    private Integer totalManager;
}
