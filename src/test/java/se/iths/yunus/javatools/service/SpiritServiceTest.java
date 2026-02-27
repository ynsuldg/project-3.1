package se.iths.yunus.javatools.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.yunus.javatools.model.Spirit;
import se.iths.yunus.javatools.repository.SpiritRepository;
import se.iths.yunus.javatools.validator.SpiritValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpiritServiceTest {

    @Mock
    private SpiritRepository spiritRepository;

    @Mock
    private SpiritValidator spiritValidator;

    @InjectMocks
    private SpiritService spiritService;

    Spirit spirit;
    Spirit redWine;
    Spirit whiteWine;
    List<Spirit> spirits;

    @BeforeEach
    public void setUp() {
        redWine = new Spirit("Red Wine",
                "Bourdo", 14.7, 18, 159.90);
        whiteWine = new Spirit("White Wine",
                "Chatu Blanich", 14.3, 12, 129.90);
        spirit = new Spirit("Whisky",
                "Macallan", 40.0, 24, 599.0);
        spirits = List.of(redWine, whiteWine);
    }

    @Test
    public void testCreateSpirit() {
        //Arrange
        when(spiritRepository.save(spirit)).thenReturn(spirit);
        //Act
        Spirit result = spiritService.createSpirit(spirit);
        //Assert
        assertNotNull(result);
        assertEquals(spirit.getTitle(), result.getTitle());
        //Verify
        verify(spiritValidator).validated(spirit);
        verify(spiritRepository).save(spirit);
    }

    @Test
    public void testCreateInvalidSpiritNameNull() {
        //Arrange
        Spirit invalidSpirit = new Spirit("",
                "Invalid", -5.0, -1, -100.0);
        //Act & Assert
        try {
            spiritService.createSpirit(invalidSpirit);
        } catch (Exception e) {
            assertEquals("Spirit type cannot be null!", e.getMessage());
        }
        //Verify
        verify(spiritValidator).validated(invalidSpirit);
    }

    @Test
    public void testSpiritMaxAPVExceeded() {
        //Arrange
        Spirit invalidSpirit = new Spirit("Vodka",
                "Invalid Vodka", 150.0, 0, 50.0);
        //Act & Assert
        try {
            spiritService.createSpirit(invalidSpirit);
        } catch (Exception e) {
            assertEquals("Alcohol Per Volume cannot be greater then 100.0%", e.getMessage());
        }
        //Verify
        verify(spiritValidator).validated(invalidSpirit);
    }

    @Test
    public void testGetAllSpirit() {
        //Arrange
        when(spiritRepository.findAll()).thenReturn(spirits);
        //Act
        List<Spirit> result = spiritService.getAllSpirit();
        //Assert
        assertEquals(2, result.size());
        //Verify
        verify(spiritRepository).findAll();
    }

    @Test
    public void testGetSpiritId() {
        //Arrange
        when(spiritRepository.findById(1L)).thenReturn(Optional.of(redWine));
        //Act
        Spirit result = spiritService.getSpiritId(1L);
        //Assert
        assertEquals("Red Wine", result.getType());
        //Verify
        verify(spiritRepository).findById(1L);
    }

    @Test
    public void testGetSpiritIdNotFound() {
        //Arrange
        when(spiritRepository.findById(99L)).thenReturn(Optional.empty());
        //Act & Assert
        try {
            spiritService.getSpiritId(99L);
        } catch (Exception e) {
            assertEquals("No Spirit99", e.getMessage());
        }
        //Verify
        verify(spiritRepository).findById(99L);
    }

    @Test
    public void testUpdateSpirit() {
        //Arrange
        Spirit updatedSpirit = new Spirit("Whisky", "Macallan",
                40.0, 24, 599.0);
        when(spiritRepository.findById(1L)).thenReturn(Optional.of(redWine));
        when(spiritRepository.save(redWine)).thenReturn(redWine);
        //Act
        Spirit result = spiritService.updateSpirit(1L, updatedSpirit);
        //Assert
        assertEquals("Whisky", redWine.getType());
        assertEquals("Macallan", redWine.getTitle());
        assertEquals(40.0, redWine.getApv());
        assertEquals(24, redWine.getAgeInMonth());
        assertEquals(599.0, redWine.getPrice());
        //Verify
        verify(spiritRepository).findById(1L);
        verify(spiritValidator).validated(updatedSpirit);
        verify(spiritRepository).save(redWine);
    }

    @Test
    public void testDeleteSpirit() {
        //Arrange
        when(spiritRepository.findById(2L)).thenReturn(Optional.of(whiteWine));
        //Act
        spiritService.deleteSpirit(2L);
        //Assert
        assertEquals("White Wine", whiteWine.getType());
        //Verify
        verify(spiritRepository).findById(2L);
        verify(spiritRepository).delete(whiteWine);
    }
}