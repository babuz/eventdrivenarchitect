package cqrs.bank.account.common.events;

import cqrs.bank.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@SuperBuilder
@Data
public class AccountClosedEvent extends BaseEvent {
}
