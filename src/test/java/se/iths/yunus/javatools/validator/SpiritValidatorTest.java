package se.iths.yunus.javatools.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.yunus.javatools.exception.*;
import se.iths.yunus.javatools.model.Spirit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpiritValidatorTest {
    private SpiritValidator spiritValidator;
    private Spirit validSpirit;

    @BeforeEach
    void setUp() {
        spiritValidator = new SpiritValidator();
        validSpirit = new Spirit("Whisky",
                "Macallan", 40.0, 24, 599.0);
    }

    @Test
    void testValidSpiritShouldPass() {
        assertDoesNotThrow(() -> spiritValidator.validated(validSpirit));
    }

    @Test
    void testNullSpiritShouldThrowException() {
        assertThrows(InvalidSpiritException.class,
                () -> spiritValidator.validated(null));
    }

    @Test
    void testInvalidBlankType() {
        Spirit spirit = new Spirit("Whisky",
                "", 40.0, 24, 599.0);
        assertThrows(InvalidSpiritException.class,
                () -> spiritValidator.validated(spirit));
    }

    @Test
    void testInvalidType() {
        validSpirit.setType("");
        assertThrows(InvalidSpiritException.class,
                () -> spiritValidator.validated(validSpirit));
    }

    @Test
    void testInvalidBlankTitle() {
        Spirit spirit = new Spirit("",
                "Macallan", 40.0, 24, 599.0);
        assertThrows(InvalidSpiritException.class,
                () -> spiritValidator.validated(spirit));
    }

    @Test
    void testInvalidTitle() {
        validSpirit.setTitle("");
        assertThrows(InvalidSpiritException.class,
                () -> spiritValidator.validated(validSpirit));
    }

    @Test
    void testInvalidNegativeAPV() {
        Spirit spirit = new Spirit("Whisky",
                "Macallan", -5.0, 24, 599.0);
        assertThrows(InvalidSpiritAPVException.class,
                () -> spiritValidator.validated(spirit));
    }

    @Test
    void testNegativeAPV() {
        validSpirit.setApv(-5.0);
        assertThrows(InvalidSpiritAPVException.class,
                () -> spiritValidator.validated(validSpirit));
    }

    @Test
    void testAPVGreaterThan100() {
        Spirit spirit = new Spirit("Whisky",
                "Macallan", 150.0, 24, 599.0);
        assertThrows(MaxSpiritAPVExceededException.class,
                () -> spiritValidator.validated(spirit));
    }

    @Test
    void testInvalidAge() {
        validSpirit.setAgeInMonth(-1);
        assertThrows(InvalidSpiritAgeInMonthException.class,
                () -> spiritValidator.validated(validSpirit));
    }

    @Test
    void testInvalidPrice() {
        validSpirit.setPrice(-100.0);
        assertThrows(InvalidSpiritPriceException.class,
                () -> spiritValidator.validated(validSpirit));
    }
}