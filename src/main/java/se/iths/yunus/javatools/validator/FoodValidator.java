package se.iths.yunus.javatools.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.iths.yunus.javatools.exception.InvalidFoodDateException;
import se.iths.yunus.javatools.exception.InvalidFoodNameException;
import se.iths.yunus.javatools.exception.InvalidFoodPriceException;
import se.iths.yunus.javatools.exception.InvalidFoodQuantityException;
import se.iths.yunus.javatools.model.Food;


import java.time.LocalDate;

@Component
public class FoodValidator {
    private static final Logger log = LoggerFactory.getLogger(FoodValidator.class);
    public void validateName(String name){
        if (name == null || name.isBlank()){
            log.warn("Validation failed: food name is null or blank.");
            throw new InvalidFoodNameException("Food item name cannot be empty.");
        }
    }

    public void validateQuantity(int quantity) {
        if (quantity < 0){
            log.warn("Validation failed: quantity {} is negative.", quantity);
            throw new InvalidFoodQuantityException("Food item quantity cannot be negative.");
        }
    }

    public void validatePrice(double price) {
        if(price < 0) {
            log.warn("Validation failed: price {} is negative.", price);
            throw new InvalidFoodPriceException("Food item price cannot be negative.");
        }
    }

    public void validateBestBefore(LocalDate date) {
        if (date == null) {
            log.warn("Validation failed: best-before date is null.");
            throw new InvalidFoodDateException("Food item date cannot be null.");
        }

        if(date.isBefore(LocalDate.now())){
            log.warn("Validation failed: best-before date {} is in the past.", date);
            throw new InvalidFoodDateException("Best-before date cannot be in the past.");
        }
    }

    public void validate(Food food) {
        log.info("Validating food item: {}", food.getName());
        validateName(food.getName());
        validateQuantity(food.getQuantity());
        validatePrice(food.getPrice());
        validateBestBefore(food.getBestBefore());
    }
}
