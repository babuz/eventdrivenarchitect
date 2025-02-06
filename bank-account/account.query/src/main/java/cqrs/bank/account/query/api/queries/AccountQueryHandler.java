package cqrs.bank.account.query.api.queries;

import cqrs.bank.account.query.api.dto.EqualityType;
import cqrs.bank.account.query.domain.AccountRepository;
import cqrs.bank.account.query.domain.BankAccount;
import cqrs.bank.cqrs.core.domain.BaseEntity;
import cqrs.bank.cqrs.core.exceptions.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountQueryHandler implements QueryHandler {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        Iterable<BankAccount> accounts = accountRepository.findAll();
        List<BaseEntity> listOfBanksAccounts = new ArrayList<>();
        accounts.forEach(listOfBanksAccounts::add);
        return listOfBanksAccounts;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        Optional<BankAccount> accounts = accountRepository.findById(query.getId());
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("Account not exist for the given Id");
        }
        return List.of(accounts.get());
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        Iterable<BankAccount> accounts = accountRepository.findByAccountHolder(query.getAccountHolder());
        if (accounts == null) {
            throw new AccountNotFoundException("Account not exist for the given the given account holder " + query.getAccountHolder());
        }
        List<BaseEntity> listOfBanksAccounts = new ArrayList<>();
        accounts.forEach(listOfBanksAccounts::add);
        return listOfBanksAccounts;
    }

    @Override
    public List<BaseEntity> handle(FindAccountsWithBalance query) {
        Iterable<BankAccount> accounts = (query.getEqualityType() == EqualityType.GREATER_THEN)
                ? accountRepository.findByBalanceGreaterThan(query.getBalance())
                : accountRepository.findByBalanceLessThan(query.getBalance());

        List<BaseEntity> listOfBanksAccounts = new ArrayList<>();
        accounts.forEach(listOfBanksAccounts::add);
        return listOfBanksAccounts;
    }
}
