package cqrs.bank.cqrs.core.exceptions;

public class AggregateNotFoundException extends RuntimeException{

    public AggregateNotFoundException(String message){
        super(message);
    }

    public AggregateNotFoundException(String message, Exception exception){
        super(message, exception);
    }
}
