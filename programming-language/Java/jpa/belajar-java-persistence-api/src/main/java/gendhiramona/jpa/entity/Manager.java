package gendhiramona.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("MANAGER")
@Getter
@Setter
public class Manager extends Employee{

    @Column(name = "total_employee")
    private Integer totalEmployee;
}
