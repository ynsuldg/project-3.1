package se.iths.yunus.javatools.exception;

public class InvalidFoodNameException extends RuntimeException {
    public InvalidFoodNameException(String message){
        super(message);
    }
}
