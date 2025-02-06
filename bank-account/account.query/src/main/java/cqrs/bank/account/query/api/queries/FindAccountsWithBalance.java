package cqrs.bank.account.query.api.queries;

import cqrs.bank.account.query.api.dto.EqualityType;
import cqrs.bank.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountsWithBalance extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
