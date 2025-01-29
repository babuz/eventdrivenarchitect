package cqrs.bank.account.cmd.api.commands;

import cqrs.bank.cqrs.core.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class DepositFundCommand extends BaseCommand {
    private double amount;
}
