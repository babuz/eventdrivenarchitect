package cqrs.bank.account.query.api.dto;

import cqrs.bank.account.common.dto.BaseResponse;
import cqrs.bank.account.query.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfoResponse extends BaseResponse {
    private List<BankAccount> accounts;

    public AccountInfoResponse(String message) {
        super(message);
    }
}
