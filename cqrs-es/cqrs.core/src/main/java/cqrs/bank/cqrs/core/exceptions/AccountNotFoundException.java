package cqrs.bank.cqrs.core.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
