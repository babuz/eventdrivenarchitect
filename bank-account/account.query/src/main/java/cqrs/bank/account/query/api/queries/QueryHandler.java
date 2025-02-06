package cqrs.bank.account.query.api.queries;

import cqrs.bank.cqrs.core.domain.BaseEntity;

import java.util.List;

public interface QueryHandler {
    List<BaseEntity> handle(FindAllAccountsQuery query);

    List<BaseEntity> handle(FindAccountByIdQuery query);

    List<BaseEntity> handle(FindAccountByHolderQuery query);

    List<BaseEntity> handle(FindAccountsWithBalance query);
}
