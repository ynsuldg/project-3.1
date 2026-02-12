package se.iths.yunus.javatools.exception;

public class InvalidPizzaException extends RuntimeException {
    public InvalidPizzaException(String message) {
        super(message);
    }
}
