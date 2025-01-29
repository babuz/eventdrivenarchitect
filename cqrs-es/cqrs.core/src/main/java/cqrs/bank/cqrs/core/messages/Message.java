package cqrs.bank.cqrs.core.messages;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Message {
    private String id;
}
