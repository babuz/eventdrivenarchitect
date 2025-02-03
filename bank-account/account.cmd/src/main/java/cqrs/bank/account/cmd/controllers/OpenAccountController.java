package cqrs.bank.account.cmd.controllers;

import cqrs.bank.account.cmd.api.commands.OpenAccountCommand;
import cqrs.bank.account.cmd.dto.OpenAccountResponse;
import cqrs.bank.cqrs.core.infrastructure.CommandDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/openBankAccount")
public class OpenAccountController {

    @Autowired
    CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<OpenAccountResponse> openNewAccount(@RequestBody OpenAccountCommand command){

        try {
            String id = UUID.randomUUID().toString();
            command.setId(id);
            commandDispatcher.send(command);

            return ResponseEntity.ok(new OpenAccountResponse("Account successfully created", id));
        }
        catch (IllegalStateException e){
            log.error("Business Illegal state exception ", e);
            return ResponseEntity.badRequest().body(new OpenAccountResponse("Invalid state bad request" + e.getMessage()));
        }
        catch (Exception e){
            log.error("Unhandled exception ", e);
            return ResponseEntity.internalServerError()
                    .body(new OpenAccountResponse("Unhandled exception server error" + e.getMessage()));

        }
    }

}
