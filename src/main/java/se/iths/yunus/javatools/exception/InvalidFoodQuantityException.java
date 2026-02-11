package se.iths.yunus.javatools.exception;

public class InvalidFoodQuantityException extends RuntimeException{
    public InvalidFoodQuantityException(String message){
        super(message);
    }
}
