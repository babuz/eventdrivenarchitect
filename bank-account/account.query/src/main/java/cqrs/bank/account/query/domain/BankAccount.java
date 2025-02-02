package cqrs.bank.account.query.domain;

import cqrs.bank.account.common.dto.AccountType;
import cqrs.bank.cqrs.core.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BankAccount extends BaseEntity {
    @Id
    private String id;
    private String accountHolder;
    private Date creationDate;
    private double balance;
    private AccountType accountType;
}
