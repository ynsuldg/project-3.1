package se.iths.yunus.javatools.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.yunus.javatools.exception.FoodNotFoundException;
import se.iths.yunus.javatools.exception.InvalidFoodPriceException;
import se.iths.yunus.javatools.exception.InvalidFoodQuantityException;
import se.iths.yunus.javatools.model.Food;
import se.iths.yunus.javatools.repository.FoodRepository;
import se.iths.yunus.javatools.validator.FoodValidator;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
    @Mock
    private FoodRepository foodRepository;

    @Mock
    private FoodValidator foodValidator;

    @InjectMocks
    private FoodService foodService;

    private Food foodSample;

    @BeforeEach
    public void setUp(){
        foodSample = new Food("Milk", true,
                false,
                "01234567892",29.90 ,
                LocalDate.now().plusDays(5),
                10);

    }
// ============================= CREATE -Tests  ======================================
    @Test
    public void create_ShouldValidateAndSave(){

        //Arrange
        doNothing().when(foodValidator).validate(foodSample);
        when(foodRepository.save(foodSample)).thenReturn(foodSample);
        //Act
        Food result = foodService.create(foodSample);
        //Assert
        verify(foodValidator).validate(foodSample);
        verify(foodRepository).save(foodSample);
        assertEquals(foodSample, result);
    }

    @Test
    public void create_ShouldThrowException_WhenValidatorFails(){

        //Arrange
        doThrow(new InvalidFoodPriceException("Invalid price")).when(foodValidator).validate(foodSample);
        //Act + Assert
        assertThrows(InvalidFoodPriceException.class, ()-> foodService.create(foodSample));

        verify(foodRepository, never()).save(any());
    }

    // ======================= READ-Tests ================================
    @Test
    public void findAllTest(){
        //Arrange
        when(foodRepository.findAll()).thenReturn(List.of(foodSample));

        // Act
        List<Food> result = foodService.findAll();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void findByIdTest(){
        //Arrange
        when(foodRepository.findById(1L)).thenReturn(Optional.of(foodSample));
        //Act
        Food result = foodService.findById(1L);
        //Assert
        assertEquals(foodSample, result);
    }

    @Test
    public void findById_ThrowExceptionTest(){
        //Arrange
        when(foodRepository.findById(1L)).thenReturn(Optional.empty());

        //Act + Assert
        assertThrows(FoodNotFoundException.class, ()-> foodService.findById(1L));

    }

    @Test
    public void findByBarcodeTest(){
        //Arrange
        when(foodRepository.findByBarcode("123456")).thenReturn(Optional.of(foodSample));

        //Act
        Food result = foodService.findByBarcode("123456");

        //Assert
        assertEquals(foodSample, result);
    }

    @Test
    public void findByBarcodeThrowExceptionTest(){
        //Arrange
        when(foodRepository.findByBarcode("123456")).thenReturn(Optional.empty());

        //Act + Assert
        assertThrows(FoodNotFoundException.class,() -> foodService.findByBarcode("123456"));
    }

    @Test
    public void findIfHasLactoseTest(){
        //Arrange
        when(foodRepository.findByBarcode("123456")).thenReturn(Optional.of(foodSample));
        //Act
        boolean result = foodService.findIfHasLactoseByBarcode("123456");

        assertTrue(result);
    }

    @Test
    public void findOutOfStockTest(){
        //Arrange
        when(foodRepository.findByQuantityLessThanEqual(0)).thenReturn(List.of(foodSample));
        // Act
        List<Food> result = foodService.findOutOfStock();

        //Assert
        assertEquals(1, result.size());
    }

    //===================================== UPDATE-Tests ===============================

    @Test
    public void updateTest(){

        //Arrange
        Food updated = new Food();
        updated.setName("New Milk");
        updated.setBarcode("1234");
        updated.setPrice(15.90);
        updated.setQuantity(5);
        updated.setBestBefore(LocalDate.now().plusDays(10));

        when(foodRepository.findById(1L)).thenReturn(Optional.of(foodSample));


        when(foodRepository.save(any())).thenReturn(foodSample);

        // Act
        Food result = foodService.update(1L, updated);

        // Assert
        verify(foodValidator).validate(updated);
        verify(foodRepository).save(foodSample);

    }

    @Test
    public void updateThrowExceptionTest(){
        //Arrange
        when(foodRepository.findById(1L)).thenReturn(Optional.empty());

        //Act + Assert
        assertThrows(FoodNotFoundException.class,()-> foodService.update(1L, foodSample));
    }

    @Test
    public void updateThrowExceptionTest2(){
        //Arrange
        doThrow(new InvalidFoodQuantityException("Invalid quantity")).when(foodValidator).validate(foodSample);

        //Act + Assert
        assertThrows(InvalidFoodQuantityException.class, ()->foodService.update(1L, foodSample));
    }

    // ================================== DELETE-Tests ======================================================

    @Test
    public void deleteTest(){
        //Arrange
        when(foodRepository.findById(1L)).thenReturn(Optional.of(foodSample));

        //Act
        foodService.delete(1L);

        //Assert
        verify(foodRepository).delete(foodSample);

    }

    @Test
    public void deleteThrowExceptionTest(){
        //Arrange
        when(foodRepository.findById(1L)).thenReturn(Optional.empty());

        //Act + Assert
        assertThrows(FoodNotFoundException.class, ()-> foodService.delete(1L));
    }

}
