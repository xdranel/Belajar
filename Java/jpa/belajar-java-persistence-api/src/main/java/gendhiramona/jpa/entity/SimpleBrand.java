package gendhiramona.jpa.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SimpleBrand {

    private String id;

    private String name;

    public SimpleBrand(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
