package cqrs.bank.account.common.events;

import cqrs.bank.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class FundDepositedEvent extends BaseEvent {
    private double amount;
}
