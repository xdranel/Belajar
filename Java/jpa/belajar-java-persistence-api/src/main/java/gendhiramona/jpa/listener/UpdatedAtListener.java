package gendhiramona.jpa.listener;

import gendhiramona.jpa.entity.UpdatedAtAware;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UpdatedAtListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdatedAt(UpdatedAtAware entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
