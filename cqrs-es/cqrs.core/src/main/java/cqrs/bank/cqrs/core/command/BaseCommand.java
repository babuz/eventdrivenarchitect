package cqrs.bank.cqrs.core.command;

import cqrs.bank.cqrs.core.messages.Message;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
public class BaseCommand extends Message {
}
