package cqrs.bank.account.cmd.infrastructure;

import cqrs.bank.account.cmd.domain.AccountAggregate;
import cqrs.bank.cqrs.core.domain.AggregateRoot;
import cqrs.bank.cqrs.core.events.BaseEvent;
import cqrs.bank.cqrs.core.handlers.EventSourcingHandlers;
import cqrs.bank.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandlers<AccountAggregate> {
    @Autowired
    EventStore eventStore;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(),
                aggregate.getUncommitedChanges(),
                aggregate.getVersion());
        aggregate.markChangesAreCompleted();
    }

    @Override
    public AccountAggregate getById(String id) {
        var aggregate = new AccountAggregate();
        var events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            aggregate.replyEvent(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }
}
