package cqrs.bank.cqrs.core.producers;

import cqrs.bank.cqrs.core.events.BaseEvent;

public interface EventProducer {

    void produce(String topic, BaseEvent event);
}
