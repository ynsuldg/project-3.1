package se.iths.yunus.javatools.service;

import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.DrinkNotFoundException;
import se.iths.yunus.javatools.model.Drink;
import se.iths.yunus.javatools.repository.DrinkRepository;
import se.iths.yunus.javatools.validator.DrinkValidator;

import java.util.List;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final DrinkValidator drinkValidator;

    public DrinkService(DrinkRepository drinkRepository, DrinkValidator drinkValidator) {
        this.drinkRepository = drinkRepository;
        this.drinkValidator = drinkValidator;
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Drink getDrink(Long id) {
        return drinkRepository.findById(id)
                .orElseThrow(() -> new DrinkNotFoundException("Drink with id " + id + " not found"));
    }

    public Drink postNewDrink(Drink drink) {
        drinkValidator.validate(drink);
        return drinkRepository.save(drink);
    }

    public Drink putExistingDrink(Long id, Drink drink) {
        Drink existing = drinkRepository.findById(id).orElseThrow(() -> new DrinkNotFoundException("Drink with id " + id + " not found"));

        drinkValidator.validate(drink);

        drink.setId(id);

        return drinkRepository.save(drink);
    }

    public void deleteDrink(Long id) {
        if (!drinkRepository.existsById(id)) {
            throw new DrinkNotFoundException("Drink with id " + id + " not found");
        }
        drinkRepository.deleteById(id);
    }
}