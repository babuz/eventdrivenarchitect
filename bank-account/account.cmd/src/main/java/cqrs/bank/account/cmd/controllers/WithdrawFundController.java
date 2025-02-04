package cqrs.bank.account.cmd.controllers;

import cqrs.bank.account.cmd.api.commands.WithdrawFundCommand;
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

@Slf4j
@RestController
@RequestMapping("/api/v1/withdrawFunds")
public class WithdrawFundController {

    @Autowired
    CommandDispatcher commandDispatcher;

    @PostMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withdrawFund(@RequestBody WithdrawFundCommand command,
                                                     @PathVariable("id") String id) {
        try {
            command.setId(id);
            commandDispatcher.send(command);
            return ResponseEntity.ok(new BaseResponse("Successfully withdrawn amount from id" + id));
        }
        catch (IllegalStateException | AggregateNotFoundException exception){
            log.error("Bad request exception ", exception);
            return ResponseEntity.badRequest().body(new BaseResponse("Bad Request " + exception.getMessage()));

        } catch (Exception e) {
            log.error("Unhandled exception ", e);
            return ResponseEntity.internalServerError().body(new BaseResponse("Internal server error " + e.getMessage()));
        }
    }
}
