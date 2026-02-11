package se.iths.yunus.javatools.service;

import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.NoPizzaFoundException;
import se.iths.yunus.javatools.model.Pizza;
import se.iths.yunus.javatools.repository.PizzaRepository;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;


    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizza() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizza(Long id) {
        return pizzaRepository.findById(id).orElseThrow(
                () -> new NoPizzaFoundException("No pizza found with id" + id));
    }

    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Long id, Pizza pizza) {
        pizza.setId(id);
        return pizzaRepository.save(pizza);

    }

    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }


}




