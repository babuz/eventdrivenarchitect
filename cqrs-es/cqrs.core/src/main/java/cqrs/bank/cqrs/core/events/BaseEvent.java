package cqrs.bank.cqrs.core.events;

import cqrs.bank.cqrs.core.messages.Message;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class BaseEvent extends Message {
    private int version;
}
