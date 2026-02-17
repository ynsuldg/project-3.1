package se.iths.yunus.javatools.validator;

import org.springframework.stereotype.Component;
import se.iths.yunus.javatools.exception.InvalidDrinkExpireDateException;
import se.iths.yunus.javatools.exception.InvalidDrinkFlavourException;
import se.iths.yunus.javatools.exception.InvalidDrinkNameException;
import se.iths.yunus.javatools.exception.InvalidDrinkPriceException;
import se.iths.yunus.javatools.model.Drink;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DrinkValidator {

    public void validate(Drink drink) {

        // NAME
        if (drink.getName() == null || drink.getName().isBlank()) {
            throw new InvalidDrinkNameException("Drink name cannot be empty");
        }

        // FLAVOUR
        if (drink.getFlavour() == null || drink.getFlavour().isBlank()) {
            throw new InvalidDrinkFlavourException("Flavour cannot be empty");
        }

        // PRICE
        if (drink.getPrice() == null || drink.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDrinkPriceException("Price must be greater than 0");
        }

        // EXPIRE DATE
        if (drink.getExpireDate() == null) {
            throw new InvalidDrinkExpireDateException("Expire date cannot be null");
        }

        LocalDate expire = drink.getExpireDate().toLocalDate();
        if (expire.isBefore(LocalDate.now())) {
            throw new InvalidDrinkExpireDateException("Expire date cannot be in the past");
        }
    }
}