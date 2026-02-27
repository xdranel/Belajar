package gendhiramona.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions_credit")
@Getter
@Setter
public class TransactionCredit extends Transaction{

    @Column(name = "credit_amount")
    private Long creditAmount;
}
