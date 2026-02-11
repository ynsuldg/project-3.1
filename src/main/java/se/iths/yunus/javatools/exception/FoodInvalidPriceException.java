package se.iths.yunus.javatools.exception;

public class FoodInvalidPriceException extends RuntimeException{
    public FoodInvalidPriceException(String message){
        super(message);
    }
}
