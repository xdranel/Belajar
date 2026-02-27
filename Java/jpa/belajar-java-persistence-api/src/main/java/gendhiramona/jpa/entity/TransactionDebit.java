package gendhiramona.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions_debit")
@Getter
@Setter
public class TransactionDebit extends Transaction{

    @Column(name = "debit_amount")
    private Long debitAmount;
}
