package cqrs.bank.account.cmd;

import cqrs.bank.account.cmd.api.commands.CloseAccountCommand;
import cqrs.bank.account.cmd.api.commands.CommandHandler;
import cqrs.bank.account.cmd.api.commands.DepositFundCommand;
import cqrs.bank.account.cmd.api.commands.OpenAccountCommand;
import cqrs.bank.account.cmd.api.commands.WithdrawFundCommand;
import cqrs.bank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	CommandDispatcher commandDispatcher;

	@Autowired
	CommandHandler commandHandler;

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	void  registerHandler(){
		commandDispatcher.registerHandler( OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler( CloseAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler( DepositFundCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler( WithdrawFundCommand.class, commandHandler::handle);
	}
}
