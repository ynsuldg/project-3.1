package se.iths.yunus.javatools.validator;

import org.springframework.stereotype.Component;
import se.iths.yunus.javatools.exception.InvalidDrinkNameException;
import se.iths.yunus.javatools.model.Drink;

@Component
public class DrinkValidator {

    public void validate(Drink drink) {
        if (drink.getName() == null || drink.getName().isBlank()) {
            throw new InvalidDrinkNameException("Drink name cannot be empty");
        }


    }
}