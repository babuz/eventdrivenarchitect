package cqrs.bank.account.query.api.controller;

import cqrs.bank.account.query.api.dto.AccountInfoResponse;
import cqrs.bank.account.query.api.dto.EqualityType;
import cqrs.bank.account.query.api.queries.FindAccountByHolderQuery;
import cqrs.bank.account.query.api.queries.FindAccountByIdQuery;
import cqrs.bank.account.query.api.queries.FindAccountsWithBalance;
import cqrs.bank.account.query.api.queries.FindAllAccountsQuery;
import cqrs.bank.account.query.domain.BankAccount;
import cqrs.bank.cqrs.core.infrastructure.QueryDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountLookupController {

    @Autowired
    QueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<AccountInfoResponse> getAllAccount() {
        List<BankAccount> accounts = new ArrayList<>();
        try {
            List<BankAccount> entities = queryDispatcher.send(new FindAllAccountsQuery());
            if (entities == null || entities.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new AccountInfoResponse(entities));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new AccountInfoResponse("internal server error"));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AccountInfoResponse> getAccountsById(@PathVariable("id") String id) {
        List<BankAccount> accounts = new ArrayList<>();
        try {
            List<BankAccount> entities = queryDispatcher.send(new FindAccountByIdQuery(id));
            if (entities == null || entities.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new AccountInfoResponse(entities));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new AccountInfoResponse("internal server error"));
        }
    }

    @GetMapping(path = "/holder/{holder}")
    public ResponseEntity<AccountInfoResponse> getAccountsByAccountHolder(@PathVariable("holder") String holder) {
        List<BankAccount> accounts = new ArrayList<>();
        try {
            List<BankAccount> entities = queryDispatcher.send(new FindAccountByHolderQuery(holder));
            if (entities == null || entities.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new AccountInfoResponse(entities));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new AccountInfoResponse("internal server error"));
        }
    }

    @GetMapping(path = "/balance/{equalityType}/{balance}")
    public ResponseEntity<AccountInfoResponse> getAccountsByBalance(
            @PathVariable("equalityType") EqualityType equalityType,
            @PathVariable("balance") double balance) {
        List<BankAccount> accounts = new ArrayList<>();
        try {
            List<BankAccount> entities = queryDispatcher.send(new FindAccountsWithBalance(equalityType, balance));
            if (entities == null || entities.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new AccountInfoResponse(entities));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new AccountInfoResponse("internal server error"));
        }
    }
}
