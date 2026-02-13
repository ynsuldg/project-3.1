package se.iths.yunus.javatools.validator;


import org.springframework.stereotype.Component;
import se.iths.yunus.javatools.exception.InvalidFoodDateException;
import se.iths.yunus.javatools.exception.InvalidFoodNameException;
import se.iths.yunus.javatools.exception.InvalidFoodPriceException;
import se.iths.yunus.javatools.exception.InvalidFoodQuantityException;
import se.iths.yunus.javatools.model.Food;


import java.time.LocalDate;

@Component
public class FoodValidator {
    public void validateName(String name){
        if (name == null || name.isBlank()){
            throw new InvalidFoodNameException("Food item name cannot be empty.");
        }
    }

    public void validateQuantity(int quantity) {
        if (quantity < 0){
            throw new InvalidFoodQuantityException("Food item quantity cannot be negative.");
        }
    }

    public void validatePrice(double price) {
        if(price < 0) {
            throw new InvalidFoodPriceException("Food item price cannot be negative.");
        }
    }

    public void validateBestBefore(LocalDate date) {
        if (date == null) {
            throw new InvalidFoodDateException("Food item date cannot be null.");
        }
        if(date.isBefore(LocalDate.now())){
            throw new InvalidFoodDateException("Best-before date cannot be in the past.");
        }
    }

    public void validate(Food food) {
        validateName(food.getName());
        validateQuantity(food.getQuantity());
        validatePrice(food.getPrice());
        validateBestBefore(food.getBestBefore());
    }
}
