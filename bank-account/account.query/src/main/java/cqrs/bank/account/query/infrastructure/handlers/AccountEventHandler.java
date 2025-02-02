package cqrs.bank.account.query.infrastructure.handlers;

import cqrs.bank.account.common.events.AccountClosedEvent;
import cqrs.bank.account.common.events.AccountOpenedEvent;
import cqrs.bank.account.common.events.FundDepositedEvent;
import cqrs.bank.account.common.events.FundWithdrawEvent;
import cqrs.bank.account.query.domain.AccountRepository;
import cqrs.bank.account.query.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder()
                .accountHolder(event.getAccountHolder())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .creationDate(event.getCreatedDate())
                .id(event.getId())
                .build();
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }

    @Override
    public void on(FundDepositedEvent event) {
        var existingBankAccount = accountRepository.findById(event.getId());
        if (existingBankAccount.isEmpty()) {
            return;
        }

        double currentBalance = existingBankAccount.get().getBalance();
        double newBalance = currentBalance + event.getAmount();
        existingBankAccount.get().setBalance(newBalance);
        accountRepository.save(existingBankAccount.get());
    }

    @Override
    public void on(FundWithdrawEvent event) {
        var existingBankAccount = accountRepository.findById(event.getId());
        if (existingBankAccount.isEmpty()) {
            return;
        } else if (existingBankAccount.get().getBalance() < event.getAmount()) {
            throw new RuntimeException("Insufficient balance exception");
        }
        double currentBalance = existingBankAccount.get().getBalance();
        double newBalance = currentBalance - event.getAmount();
        existingBankAccount.get().setBalance(newBalance);
        accountRepository.save(existingBankAccount.get());
    }
}
