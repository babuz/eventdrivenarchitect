package cqrs.bank.account.query.infrastructure.handlers;

import cqrs.bank.account.common.events.AccountClosedEvent;
import cqrs.bank.account.common.events.AccountOpenedEvent;
import cqrs.bank.account.common.events.FundDepositedEvent;
import cqrs.bank.account.common.events.FundWithdrawEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(AccountClosedEvent event);
    void on(FundDepositedEvent event);
    void on(FundWithdrawEvent event);
}
