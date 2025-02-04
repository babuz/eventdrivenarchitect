package cqrs.bank.account.cmd.controllers;

import cqrs.bank.cqrs.core.events.EventModel;
import cqrs.bank.cqrs.core.infrastructure.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventsController {

    @Autowired
    EventStore eventStore;

    @GetMapping
    public ResponseEntity<List<EventModel>> getAllEvents(){
        return ResponseEntity.ok(eventStore.getAllEvents());
    }
}
