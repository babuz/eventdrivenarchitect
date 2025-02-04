package cqrs.bank.account.cmd.infrastructure;

import cqrs.bank.account.cmd.domain.AccountAggregate;
import cqrs.bank.account.cmd.domain.EventStoreRepository;
import cqrs.bank.cqrs.core.events.BaseEvent;
import cqrs.bank.cqrs.core.events.EventModel;
import cqrs.bank.cqrs.core.exceptions.AggregateNotFoundException;
import cqrs.bank.cqrs.core.exceptions.ConcurrencyException;
import cqrs.bank.cqrs.core.infrastructure.EventStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AccountEventStore implements EventStore {

    @Autowired
    private EventStoreRepository eventStoreRepository;

    @Autowired
    private AccountEventProducer accountEventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var existingEvents = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && existingEvents.get(existingEvents.size() - 1).getVersion() != expectedVersion) {
            log.error("expected version {} is not -1 for the aggregateId {}", expectedVersion, aggregateId);
            throw new ConcurrencyException(" expected version is correct, concurrency exception" + expectedVersion);
        }
        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);

            EventModel eventModel = EventModel.builder()
                    .eventData(event)
                    .eventType(event.getClass().getTypeName())
                    .timeStamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .build();

            EventModel persistedEvent = eventStoreRepository.save(eventModel);

            if (!persistedEvent.getId().isEmpty()) {
                log.info("logging the event to Kafka {}", persistedEvent);
                accountEventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var existingEvents = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (existingEvents == null || existingEvents.isEmpty()) {
            log.info("Aggregate not found for the given aggregate Id : {}", aggregateId);
            throw new AggregateNotFoundException("Aggregate not found for this aggregateId :" + aggregateId);
        }
        return existingEvents.stream().map(EventModel::getEventData).toList();
    }
}
