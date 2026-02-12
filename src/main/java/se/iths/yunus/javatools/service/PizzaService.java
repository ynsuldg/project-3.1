package se.iths.yunus.javatools.service;

import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.NoPizzaFoundException;
import se.iths.yunus.javatools.model.Pizza;
import se.iths.yunus.javatools.repository.PizzaRepository;
import se.iths.yunus.javatools.validator.PizzaValidator;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaValidator pizzaValidator;


    public PizzaService(PizzaRepository pizzaRepository, PizzaValidator pizzaValidator) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaValidator = pizzaValidator;
    }

    public List<Pizza> getAllPizza() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizza(Long id) {
        return pizzaRepository.findById(id).orElseThrow(
                () -> new NoPizzaFoundException("No pizza found with id" + id));


    }

    public Pizza createPizza(Pizza pizza) {
        pizzaValidator.validate(pizza);
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        Pizza existing = pizzaRepository.findById(id)
                .orElseThrow(() -> new NoPizzaFoundException("Ingen pizza hittades med id" + id));

        pizzaValidator.validate(pizza);

        existing.setName(pizza.getName());
        existing.setTopping(pizza.getTopping());
        existing.setCheese(pizza.getCheese());
        existing.setPrice(pizza.getPrice());
        
        return pizzaRepository.save(existing);


    }

    public void deletePizza(Long id) {
        Pizza existing = pizzaRepository.findById(id)
                .orElseThrow(() -> new NoPizzaFoundException("Ingen pizza med id" + id));
        pizzaRepository.deleteById(existing.getId());
    }


}




