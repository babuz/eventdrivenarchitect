package cqrs.bank.account.cmd.api.commands;

import cqrs.bank.account.cmd.domain.AccountAggregate;
import cqrs.bank.account.cmd.infrastructure.AccountEventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler {

    @Autowired
    AccountEventSourcingHandler eventSourcingHandler;

    @Override
    public void handle(OpenAccountCommand command) {
        var aggregate = new AccountAggregate(command);
        eventSourcingHandler.save(aggregate);

    }

    @Override
    public void handle(CloseAccountCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.closeAccount();
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DepositFundCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        if (command.getAmount() > aggregate.getBalance()) {
            throw new IllegalStateException(" withdraw amount is greater than balance amount");
        }
        aggregate.depositFund(command.getAmount());
        eventSourcingHandler.save(aggregate);

    }

    @Override
    public void handle(WithdrawFundCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.withdrawFund(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }
}
