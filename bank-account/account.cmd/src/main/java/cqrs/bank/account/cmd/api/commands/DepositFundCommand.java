package cqrs.bank.account.cmd.api.commands;

import cqrs.bank.cqrs.core.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DepositFundCommand extends BaseCommand {
    private double amount;
}
