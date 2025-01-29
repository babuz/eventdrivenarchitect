package cqrs.bank.cqrs.core.infrastructure;

import cqrs.bank.cqrs.core.command.BaseCommand;
import cqrs.bank.cqrs.core.command.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
