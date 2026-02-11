package se.iths.yunus.javatools.exception;

public class FoodLowStockException extends RuntimeException {
    public FoodLowStockException(String message) {
        super(message);
    }
}
