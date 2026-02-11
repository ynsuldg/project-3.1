package se.iths.yunus.javatools.exception;

public class InvalidFoodDateException extends RuntimeException{
    public InvalidFoodDateException(String message) {
        super(message);
    }
}
