package cqrs.bank.account.query.api.queries;

import cqrs.bank.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FindAccountByHolderQuery extends BaseQuery {
    private String accountHolder;
}
