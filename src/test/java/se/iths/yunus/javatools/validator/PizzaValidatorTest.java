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
        // instans av pizzavalidator
        pizzaValidator = new PizzaValidator();
    }

    @Test
    @DisplayName("Exception när pizzan är giltig")
    public void testValidateWhenPizzaIsValid() {
        Pizza pizza = new Pizza(null, "Hawaii", "Tomat", "Ja", 99);
        assertDoesNotThrow(() ->
                pizzaValidator.validate(pizza)
        );

    }


    @Test
    @DisplayName("Testar exception när pizza är null")
    public void testValidateThrowsWhenPizzaisNull() {
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(null)
        );
    }

    @Test
    @DisplayName("Testar exception när namnet är tomt")
    public void TestValidateWhenNameIsBlank() {
        Pizza pizza = new Pizza(null, "", "Gurka", "Ja", 99);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza)
        );

    }

    @Test
    @DisplayName("när namnet är tomt och har mellanrum")
    public void testValidateThrowsWhenNameisEmpty() {
        Pizza pizza = new Pizza(null, " ", "Tomat", "Ja", 199);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza)
        );


    }

    @Test
    @DisplayName("När topping är tomt")
    public void testValidateWhenToppingisBlank() {
        Pizza pizza = new Pizza(null, "Hawaii", "", "Ja", 89);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza));

    }

    @Test
    @DisplayName("När topping är tomt och har mellanrum")
    public void testValidateWhenToppingisEmpty() {
        Pizza pizza = new Pizza(null, "Hawaii", " ", "Ja", 89);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza));


    }

    @Test
    @DisplayName("Exception när ost är tomt")
    public void testValidateThrowsWhenCheeseIsBlank() {
        Pizza pizza = new Pizza(null, "Kebab", "Feferoni", "", 149);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza));
    }

    @Test
    @DisplayName("Exception när ost är tomt och har mellanrum")
    public void testValidateThrowswhenCheeseisEmpty() {
        Pizza pizza = new Pizza(null, "Viking", "Gurka", " ", 99);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza));


    }

    @Test
    @DisplayName("Exception när priset är negativt")
    public void testValidateWhenPriceisNegative() {
        Pizza pizza = new Pizza(null, "Margarita", "Tomat", "Ja", -99);
        assertThrows(InvalidPizzaException.class, () ->
                pizzaValidator.validate(pizza));
    }

}