package cqrs.bank.account.query.infrastructure.consumers;

import cqrs.bank.account.common.events.AccountClosedEvent;
import cqrs.bank.account.common.events.AccountOpenedEvent;
import cqrs.bank.account.common.events.FundDepositedEvent;
import cqrs.bank.account.common.events.FundWithdrawEvent;
import cqrs.bank.account.query.infrastructure.handlers.AccountEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer {
    @Autowired
    private AccountEventHandler handler;

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountOpenedEvent event, Acknowledgment acknowledgment) {
        handler.on(event);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(AccountClosedEvent event, Acknowledgment acknowledgment) {
        handler.on(event);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "FundDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundDepositedEvent event, Acknowledgment acknowledgment) {
        handler.on(event);
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "FundWithdrawEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consume(FundWithdrawEvent event, Acknowledgment acknowledgment) {
        handler.on(event);
        acknowledgment.acknowledge();
    }
}
