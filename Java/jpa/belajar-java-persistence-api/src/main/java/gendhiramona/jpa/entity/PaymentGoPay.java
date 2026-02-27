package gendhiramona.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments_gopay")
@Getter
@Setter
public class PaymentGoPay extends Payment{

    @Column(name = "gopay_id")
    private String gopayId;
}
