package se.iths.yunus.javatools.service;

import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.NoDrinkFoundException;
import se.iths.yunus.javatools.model.Drink;
import se.iths.yunus.javatools.repository.DrinkRepository;

import java.util.List;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> findAll() {
        return drinkRepository.findAll();
    }

    public Drink findById(Long id) {
        return drinkRepository.findById(id).orElseThrow(() -> new NoDrinkFoundException("Drink with id " + id + " not found"));
    }

    public Drink putDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    public Drink postDrink(Long id, Drink drink) {
        drink.setId(id);
        return drinkRepository.save(drink);
    }

    public void deleteDrink(Long id) {
        drinkRepository.deleteById(id);
    }
}
