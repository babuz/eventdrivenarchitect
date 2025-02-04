package cqrs.bank.account.cmd.controllers;

import cqrs.bank.account.cmd.api.commands.CloseAccountCommand;
import cqrs.bank.account.common.dto.BaseResponse;
import cqrs.bank.cqrs.core.exceptions.AggregateNotFoundException;
import cqrs.bank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping("/api/v1/closeAccount")
public class CloseAccountController {

    @Autowired
    CommandDispatcher commandDispatcher;

    @PostMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable("id") String id) {
        CloseAccountCommand command = CloseAccountCommand.builder()
                .build();
        command.setId(id);

        try {
            commandDispatcher.send(command);
            return ResponseEntity.ok(new BaseResponse("account deleted successfully" + id));
        } catch (IllegalStateException | AggregateNotFoundException ie) {
            log.error("illegal state exeption bad request", ie);
            return ResponseEntity.badRequest().body(new BaseResponse("bad request deposited to the account" + ie.getMessage()));
        } catch (Exception e) {
            log.error("Internal server Error");
            var safeErrorMessage = MessageFormat.format("Error while process request deposit fund to the account {0}", id);
            return ResponseEntity.internalServerError().body(new BaseResponse(safeErrorMessage + e.getMessage()));
        }
    }
}
