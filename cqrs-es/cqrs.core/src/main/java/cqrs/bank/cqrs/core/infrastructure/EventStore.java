package cqrs.bank.cqrs.core.infrastructure;

import cqrs.bank.cqrs.core.events.BaseEvent;
import cqrs.bank.cqrs.core.events.EventModel;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);

    //Created for my purpose to view all events in browser;
    List<EventModel> getAllEvents();
}
