package se.iths.yunus.javatools.exception;

public class FoodAlreadyExistsException extends RuntimeException{
    public FoodAlreadyExistsException(String message) {
        super(message);
    }
}
