package cqrs.bank.cqrs.core.exceptions;

public class ConcurrencyException extends RuntimeException{
    public ConcurrencyException(String message){
        super(message);
    }

    public ConcurrencyException(String message, Exception exception){
        super(message, exception);
    }
}
