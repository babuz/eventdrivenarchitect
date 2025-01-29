package cqrs.bank.account.cmd.api.commands;

import cqrs.bank.cqrs.core.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class WithdrawFundCommand extends BaseCommand {
    private double amount;
}
