package se.iths.yunus.javatools.exception;

public class InvalidFoodPriceException extends RuntimeException{
    public InvalidFoodPriceException(String message) {
        super(message);
    }
}
