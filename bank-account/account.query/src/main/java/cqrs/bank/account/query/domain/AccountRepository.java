package cqrs.bank.account.query.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
    List<BankAccount> findByAccountHolder(String accountHolder);

    List<BankAccount> findByBalanceGreaterThan(double balanceIsGreaterThan);

    List<BankAccount> findByBalanceLessThan(double balanceIsGreaterThan);
}
