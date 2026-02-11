package se.iths.yunus.javatools.exception;

public class FoodOutOfStockException extends RuntimeException{
    public FoodOutOfStockException(String message) {
        super(message);
    }
}
