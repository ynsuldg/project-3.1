package se.iths.yunus.javatools.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.yunus.javatools.exception.InvalidPizzaException;
import se.iths.yunus.javatools.model.Pizza;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PizzaValidatorTest {

    private PizzaValidator pizzaValidator;

    @BeforeEach
    public void setUp() {
        pizzaValidator = new PizzaValidator();
    }

    @Test
    @DisplayName("Testar att validate kastar exception när pizza är null")
    public void testValidateThrowsWhenPizzaIsNull() {
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(null)
        );
    }

    @Test
    @DisplayName("Testar validate med korrekt pizza")
    public void testValidateDoesNotThrowForValidPizza() {
        Pizza pizza = new Pizza(null, "Margarita", "Tomat", "Mozzarella", 100);
        assertDoesNotThrow(() ->
                pizzaValidator.validate(pizza)
        );
    }

    @Test
    @DisplayName("Testar att validate kastar exception när namnet är tomt")
    public void testValidateThrowsWhenNameIsBlank() {
        Pizza pizza = new Pizza(null, "", "Tomat", "Mozzarella", 100);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza)
        );
    }
}
