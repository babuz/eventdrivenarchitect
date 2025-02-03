package cqrs.bank.account.cmd.domain;

import cqrs.bank.account.cmd.api.commands.OpenAccountCommand;
import cqrs.bank.account.common.events.AccountClosedEvent;
import cqrs.bank.account.common.events.AccountOpenedEvent;
import cqrs.bank.account.common.events.FundDepositedEvent;
import cqrs.bank.account.common.events.FundWithdrawEvent;
import cqrs.bank.cqrs.core.domain.AggregateRoot;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand openAccountCommand) {
        raiseEvent(AccountOpenedEvent.builder()
                .accountHolder(openAccountCommand.getAccountHolder())
                .accountType(openAccountCommand.getAccountType())
                .id(openAccountCommand.getId())
                .createdDate(new Date())
                .openingBalance(openAccountCommand.getOpeningBalance())
                .build());
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.balance = event.getOpeningBalance();
        this.active = true;
    }

    public void depositFund(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Amount can't be deposited to closed account");
        }

        if (amount <= 0) {
            throw new IllegalStateException("Amount should be greate then zero");
        }

        raiseEvent(FundDepositedEvent.builder()
                .amount(amount)
                .id(this.id)
                .build());
    }

    //apply for eachEvent
    public void apply(FundDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFund(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Amount can't be withdrawn from closed account");
        }
        if (amount <= 0) {
            throw new IllegalStateException("Amount should be greater then zero");
        }
        if (this.balance < amount) {
            throw new IllegalStateException("Amount should be greater then the balance amount");
        }

        raiseEvent(FundWithdrawEvent.builder()
                .amount(amount)
                .id(this.id)
                .build());
    }

    public void apply(FundWithdrawEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount() {
        if (!this.active) {
            throw new IllegalStateException("Amount can't be withdrawn from closed account");
        }

        raiseEvent(AccountClosedEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
