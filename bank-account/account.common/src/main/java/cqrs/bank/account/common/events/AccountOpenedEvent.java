package cqrs.bank.account.common.events;

import cqrs.bank.account.common.dto.AccountType;
import cqrs.bank.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
    @Getter
    @Setter
    private String accountHolder;
    @Getter
    private AccountType accountType;
    @Getter
    private Date createdDate;
    @Getter
    @Setter
    private double openingBalance;
}
