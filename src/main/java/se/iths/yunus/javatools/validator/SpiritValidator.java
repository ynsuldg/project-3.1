package se.iths.yunus.javatools.validator;

import org.springframework.stereotype.Component;
import se.iths.yunus.javatools.exception.*;
import se.iths.yunus.javatools.model.Spirit;

@Component
public class SpiritValidator {

    public void validated(Spirit spirit) {
        if (spirit == null) {
            throw new InvalidSpiritException("Spirit cannot be null!");
        }
        validatedType(spirit.getType());
        validatedName(spirit.getTitle());
        validatedAPV(spirit.getApv());
        validatedAge(spirit.getAgeInMonth());
        validatedPrise(spirit.getPrise());
    }

    private void validatedType(String type) {
        if (type == null || type.isBlank()) {
            throw new InvalidSpiritException("Spirit type cannot be null!");
        }
    }

    private void validatedName(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidSpiritException("Spirit title cannot be null!");
        }
    }

    private void validatedAPV(double apv) {
        if (apv < 0.0) {
            throw new InvalidSpiritAPVException("Alcohol Per Volume cannot be negativ!");
        }
        if (apv > 100.0) {
            throw new MaxSpiritAPVExceededException("Alcohol Per Volume cannot be greater then 100.0%");
        }
    }

    private void validatedAge(int ageInMonth) {
        if (ageInMonth < 0) {
            throw new InvalidSpiritAgeInMonthException("Spirit age cannot be negativ!");
        }
    }

    private void validatedPrise(double prise) {
        if (prise < 0) {
            throw new InvalidSpiritPriseException("Spirit prise cannot be negativ!");
        }
    }
}
