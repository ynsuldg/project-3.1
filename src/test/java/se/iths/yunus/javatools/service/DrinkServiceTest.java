package se.iths.yunus.javatools.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.yunus.javatools.exception.DrinkNotFoundException;
import se.iths.yunus.javatools.model.Drink;
import se.iths.yunus.javatools.repository.DrinkRepository;
import se.iths.yunus.javatools.validator.DrinkValidator;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DrinkServiceTest {

    private DrinkRepository drinkRepository;
    private DrinkValidator drinkValidator;
    private DrinkService drinkService;

    @BeforeEach
    void setUp() {
        drinkRepository = mock(DrinkRepository.class);
        drinkValidator = mock(DrinkValidator.class);

        drinkService = new DrinkService(drinkRepository, drinkValidator);
    }

    // GET ALL
    @Test
    void getAllDrinksShouldReturnList() {
        Drink d1 = new Drink("Nocco", "Orange", new BigDecimal("25.00"), Date.valueOf("2026-03-21"));
        Drink d2 = new Drink("Powerking", "Strawberry", new BigDecimal("15.00"), Date.valueOf("2026-05-05"));
        when(drinkRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Drink> result = drinkService.getAllDrinks();

        assertEquals(2, result.size());
        verify(drinkRepository).findAll();
    }

    // GET ONE
    @Test
    void getDrinkValidIdShouldReturnDrink() {
        Drink drink = new Drink("Nocco", "Orange", new BigDecimal("25.00"), Date.valueOf("2026-03-21"));
        when(drinkRepository.findById(1L)).thenReturn(Optional.of(drink));

        Drink result = drinkService.getDrink(1L);

        assertEquals("Nocco", result.getName());
        verify(drinkRepository).findById(1L);
    }

    @Test
    void getDrinkInvalidIdShouldThrow() {
        when(drinkRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(DrinkNotFoundException.class, () -> drinkService.getDrink(99L));
    }

    // NEW
    @Test
    void postNewDrinkShouldValidateAndSave() {
        Drink drink = new Drink("Powerking", "Strawberry", new BigDecimal("15.00"), Date.valueOf("2026-05-05"));
        when(drinkRepository.save(drink)).thenReturn(drink);

        Drink result = drinkService.postNewDrink(drink);

        verify(drinkValidator).validate(drink);
        verify(drinkRepository).save(drink);
        assertEquals(drink, result);
    }

    // UPDATE
    @Test
    void putExistingDrinkValidIdShouldUpdate() {
        Drink drinkToUpdate = new Drink("Powerking", "Strawberry", new BigDecimal("15.00"), Date.valueOf("2026-05-05"));
        drinkToUpdate.setId(1L);

        when(drinkRepository.findById(1L)).thenReturn(Optional.of(drinkToUpdate));
        when(drinkRepository.save(drinkToUpdate)).thenReturn(drinkToUpdate);

        Drink result = drinkService.putExistingDrink(1L, drinkToUpdate);

        verify(drinkValidator).validate(drinkToUpdate);
        verify(drinkRepository).save(drinkToUpdate);
        assertEquals(1L, result.getId());
        assertEquals("Powerking", result.getName());
    }

    @Test
    void putExistingDrinkInvalidIdShouldThrow() {
        Drink updatedDrink = new Drink("Powerking", "Strawberry", new BigDecimal("15.00"), Date.valueOf("2026-05-05"));

        when(drinkRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(DrinkNotFoundException.class, () -> drinkService.putExistingDrink(99L, updatedDrink));
        verify(drinkValidator, never()).validate(any());
    }

    // DELETE
    @Test
    void deleteDrinkValidIdShouldCallRepository() {
        when(drinkRepository.existsById(1L)).thenReturn(true);

        drinkService.deleteDrink(1L);

        verify(drinkRepository).existsById(1L);
        verify(drinkRepository).deleteById(1L);
    }

    @Test
    void deleteDrinkInvalidIdShouldThrow() {
        when(drinkRepository.existsById(99L)).thenReturn(false);

        assertThrows(DrinkNotFoundException.class, () -> drinkService.deleteDrink(99L));
        verify(drinkRepository, never()).deleteById(anyLong());
    }
}