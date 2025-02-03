package cqrs.bank.account.cmd.api.commands;

import cqrs.bank.account.common.dto.AccountType;
import cqrs.bank.cqrs.core.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountCommand extends BaseCommand {
    @Getter
    private String accountHolder;
    private AccountType accountType;
    @Getter
    private double openingBalance;
}
