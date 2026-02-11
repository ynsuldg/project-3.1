package se.iths.yunus.javatools.validator;

import org.springframework.stereotype.Component;
import se.iths.yunus.javatools.exception.InvalidDrinkNameException;
import se.iths.yunus.javatools.exception.InvalidDrinkPriceException;
import se.iths.yunus.javatools.model.Drink;

import java.math.BigDecimal;

@Component
public class DrinkValidator {

    public void validate(Drink drink) {
        if (drink.getName() == null || drink.getName().isBlank()) {
            throw new InvalidDrinkNameException("Drink name cannot be empty");
        }

        if (drink.getPrice() == null || drink.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDrinkPriceException("Price must be greater than 0");
        }
    }
}