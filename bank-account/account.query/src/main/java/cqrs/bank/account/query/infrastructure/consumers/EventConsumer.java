package cqrs.bank.account.query.infrastructure.consumers;

import cqrs.bank.account.common.events.AccountClosedEvent;
import cqrs.bank.account.common.events.AccountOpenedEvent;
import cqrs.bank.account.common.events.FundDepositedEvent;
import cqrs.bank.account.common.events.FundWithdrawEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment acknowledgment);

    void consume(@Payload AccountClosedEvent event, Acknowledgment acknowledgment);

    void consume(@Payload FundDepositedEvent event, Acknowledgment acknowledgment);

    void consume(@Payload FundWithdrawEvent event, Acknowledgment acknowledgment);
}
