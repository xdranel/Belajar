package gendhiramona.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class DepartmentId implements Serializable {

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "department_id")
    private String departmentId;
}
