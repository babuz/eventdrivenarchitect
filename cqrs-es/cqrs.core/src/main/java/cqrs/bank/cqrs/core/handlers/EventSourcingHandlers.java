package cqrs.bank.cqrs.core.handlers;

import cqrs.bank.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandlers<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);

}
