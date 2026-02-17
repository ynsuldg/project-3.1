package se.iths.yunus.javatools.exception;

public class InvalidDrinkExpireDateException extends RuntimeException {
    public InvalidDrinkExpireDateException(String message) {
        super(message);
    }
}
