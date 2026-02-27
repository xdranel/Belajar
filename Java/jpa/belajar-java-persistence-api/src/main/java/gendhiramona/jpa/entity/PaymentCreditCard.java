package gendhiramona.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments_credit_card")
@Getter
@Setter
public class PaymentCreditCard extends Payment {

    @Column(name = "masked_card")
    private String maskedCard;

    private String bank;
}
