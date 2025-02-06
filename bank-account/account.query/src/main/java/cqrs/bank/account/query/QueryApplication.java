package cqrs.bank.account.query;

import cqrs.bank.account.query.api.queries.FindAccountByHolderQuery;
import cqrs.bank.account.query.api.queries.FindAccountByIdQuery;
import cqrs.bank.account.query.api.queries.FindAccountsWithBalance;
import cqrs.bank.account.query.api.queries.FindAllAccountsQuery;
import cqrs.bank.account.query.api.queries.QueryHandler;
import cqrs.bank.cqrs.core.infrastructure.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryApplication {

	@Autowired
	QueryDispatcher queryDispatcher;

	@Autowired
	QueryHandler queryHandler;

	public static void main(String[] args) {
		SpringApplication.run(QueryApplication.class, args);
	}

	@PostConstruct
	public void registerQueryHandler(){
		queryDispatcher.registerHandler(FindAllAccountsQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByIdQuery.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountsWithBalance.class, queryHandler::handle);
		queryDispatcher.registerHandler(FindAccountByHolderQuery.class, queryHandler::handle);
	}
}
