package cqrs.bank.account.cmd.controllers;

import cqrs.bank.account.cmd.api.commands.DepositFundCommand;
import cqrs.bank.account.common.dto.BaseResponse;
import cqrs.bank.cqrs.core.exceptions.AggregateNotFoundException;
import cqrs.bank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@RequestMapping("/api/v1/depositFund")
@Slf4j
public class DepositFundsController {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> depositFunds(
            @PathVariable("id") String id,
            @RequestBody DepositFundCommand depositFundCommand) {
        try {
            depositFundCommand.setId(id);
            commandDispatcher.send(depositFundCommand);
            return ResponseEntity.ok(new BaseResponse("successfully deposited to the account"));
        } catch (IllegalStateException | AggregateNotFoundException ie ) {
            log.error("illegal state exeption bad request", ie);
            return ResponseEntity.badRequest().body(new BaseResponse("bad request deposited to the account" + ie.getMessage()));
        } catch (Exception e) {
            log.error("Internal server Error");
            var safeErrorMessage = MessageFormat.format("Error while process request deposit fund to the account {0}", id);
            return ResponseEntity.internalServerError().body(new BaseResponse(safeErrorMessage + e.getMessage()));
        }
    }
}
