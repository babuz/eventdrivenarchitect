package cqrs.bank.account.query.infrastructure.handlers;

import cqrs.bank.cqrs.core.domain.BaseEntity;
import cqrs.bank.cqrs.core.infrastructure.QueryDispatcher;
import cqrs.bank.cqrs.core.queries.BaseQuery;
import cqrs.bank.cqrs.core.queries.QueryHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountQueryDispatcher implements QueryDispatcher {

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public <U extends BaseEntity> List<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No query handlers was registered");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("More than one query handlers registered");
        }
        return handlers.get(0).handle(query);
    }
}
