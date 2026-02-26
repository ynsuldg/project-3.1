package se.iths.yunus.javatools.validator;

import org.springframework.stereotype.Component;
import se.iths.yunus.javatools.exception.InvalidPizzaException;
import se.iths.yunus.javatools.model.Pizza;

@Component
public class PizzaValidator {

    //Huvudmetod för att validera en Pizza
    public void validate(Pizza pizza) {
        if (pizza == null) {
            throw new InvalidPizzaException("Pizza cannot be null");
        }
        validateName(pizza.getName());
        validateTopping(pizza.getTopping());
        validateCheese(pizza.getCheese());
        validatePrice(pizza.getPrice());
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidPizzaException("Pizza namn kan inte vara tomt");
        }
    }

    private void validateTopping(String topping) {
        if (topping == null || topping.isBlank()) {
            throw new InvalidPizzaException("Topping kan inte vara tomt");
        }
    }

    private void validateCheese(String cheese) {
        if (cheese == null || cheese.isBlank()) {
            throw new InvalidPizzaException("Ost kan inte vara tomt");
        }
    }

    private void validatePrice(int price) {
        if (price < 0) {
            throw new InvalidPizzaException("Pris får inte vara negativt");

        }

    }

}
