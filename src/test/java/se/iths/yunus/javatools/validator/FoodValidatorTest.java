package se.iths.yunus.javatools.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.yunus.javatools.exception.InvalidFoodDateException;
import se.iths.yunus.javatools.exception.InvalidFoodNameException;
import se.iths.yunus.javatools.exception.InvalidFoodPriceException;
import se.iths.yunus.javatools.exception.InvalidFoodQuantityException;
import se.iths.yunus.javatools.model.Food;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FoodValidatorTest {
    private FoodValidator foodValidator;
    private Food foodSample;

    @BeforeEach
    public void setUp(){
        foodValidator = new FoodValidator();
        foodSample = new Food("Milk", true,
                false, "123456", 25.0,
                LocalDate.now().plusDays(10), 20);
    }

// =============================validateName ========================================
    @Test
    public void validateNameThrowExceptionTest(){
        //Arrange
        foodSample.setName(" ");

        //Act + Assert
        assertThrows(InvalidFoodNameException.class,()-> foodValidator.validateName(foodSample.getName()));

    }
    @Test
    public void validateNameNotThrowExceptionTest(){
        //Arrange + Act + Assert

        assertDoesNotThrow(()-> foodValidator.validateName(foodSample.getName()));

    }
// =============================== validateQuantity =================================
    @Test
    public void validateQuantityThrowExceptionTest(){
        //Arrange
        foodSample.setQuantity(-5);

        //Act + Assert
        assertThrows(InvalidFoodQuantityException.class,()-> foodValidator.validateQuantity(foodSample.getQuantity()));
    }

    @Test
    public void validateQuantityNotThrowExceptionTest(){
        // Arrange + Act + Assert

        assertDoesNotThrow(()->foodValidator.validateQuantity(foodSample.getQuantity()));
    }

    // ==============================validatePrice =====================================
    @Test
    public void validatePriceThrowExceptionTest(){
        //Arrange
        foodSample.setPrice(-10);

        //Act + Assert
        assertThrows(InvalidFoodPriceException.class, ()->foodValidator.validatePrice(foodSample.getPrice()));
    }

    @Test
    public void validatePriceNotThrowExceptionTest(){
        // Arrange + Act + Assert
        assertDoesNotThrow(()->foodValidator.validatePrice(foodSample.getPrice()));
    }


    // ==============================validateBestBefore ======================================

    @Test
    public void validateDateInThePastThrowExceptionTest(){
        //Arrange
        foodSample.setBestBefore(LocalDate.now().minusDays(10));

        //Act + Assert
        assertThrows(InvalidFoodDateException.class, ()->foodValidator.validateBestBefore(foodSample.getBestBefore()));

    }

    @Test
    public void validateDateAsNullThrowExceptionTest(){
        //Arrange
        foodSample.setBestBefore(null);
        //Act + Assert
        assertThrows(InvalidFoodDateException.class, ()->foodValidator.validateBestBefore(foodSample.getBestBefore()));

    }

    @Test
    public void validateBestBefore_NotThrowExceptionTest(){
        //Arrange + Act + Test
        assertDoesNotThrow(()->foodValidator.validateBestBefore(foodSample.getBestBefore()));
    }

}
