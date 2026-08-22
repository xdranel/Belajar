package gendhiramona.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @Embedded
    private Name name;

    @ElementCollection
    @CollectionTable(name = "hobbies", joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"))
    @Column(name = "name")
    private List<String> hobbies;

    @ElementCollection
    @CollectionTable(name = "skills", joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, Integer> skills;

    @Transient
    private String fullName;

    @PostLoad
    public void postLoad() {
//        String middleNamePart = "";
//        if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
//            middleNamePart = " " + name.getMiddleName();
//        }
//
//        String lastNamePart = "";
//        if (name.getLastName() != null && !name.getLastName().isEmpty()) {
//            lastNamePart = " " + name.getLastName();
//        }
//        fullName = name.getTitle() + " " + name.getFirstName() + middleNamePart + lastNamePart;

        fullName = name.getTitle() + " " + name.getFirstName() +
                (name.getMiddleName() != null && !name.getMiddleName().isEmpty() ? " " + name.getMiddleName() : "") +
                (name.getLastName() != null && !name.getLastName().isEmpty() ? " " + name.getLastName() : "");

    }
}
