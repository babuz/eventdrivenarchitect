package cqrs.bank.account.cmd.infrastructure;

import cqrs.bank.cqrs.core.command.BaseCommand;
import cqrs.bank.cqrs.core.command.CommandHandlerMethod;
import cqrs.bank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type,
                                                        CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        var handlers = this.routes.get(command.getClass());

        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No handlers registered for this class:  " + command.getId());
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Multiple handlers registered for this class:  " + command.getId());
        }
        handlers.get(0).handle(command);
    }
}
