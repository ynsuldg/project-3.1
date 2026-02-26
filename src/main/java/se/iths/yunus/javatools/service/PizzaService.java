package se.iths.yunus.javatools.service;

import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.NoPizzaFoundException;
import se.iths.yunus.javatools.model.Pizza;
import se.iths.yunus.javatools.repository.PizzaRepository;
import se.iths.yunus.javatools.validator.PizzaValidator;

import java.util.List;

// affärslogiken
@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    //affärsregler
    private final PizzaValidator pizzaValidator;

    //konstruktorinjektion
    public PizzaService(PizzaRepository pizzaRepository, PizzaValidator pizzaValidator) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaValidator = pizzaValidator;
    }

    // hämtar alla pizzor
    public List<Pizza> getAllPizza() {
        return pizzaRepository.findAll();
    }

    // hämtar pizza med id
    public Pizza getPizza(Long id) {
        return pizzaRepository.findById(id).orElseThrow(
                () -> new NoPizzaFoundException("No pizza found with id" + id));


    }

    //skapar pizza
    public Pizza createPizza(Pizza pizza) {
        pizzaValidator.validate(pizza);
        return pizzaRepository.save(pizza);
    }

    //uppdaterar pizza
    public Pizza updatePizza(Long id, Pizza pizza) {
        // kollar om pizzan finns
        Pizza existing = pizzaRepository.findById(id)
                .orElseThrow(() -> new NoPizzaFoundException("Ingen pizza hittades med id" + id));

        pizzaValidator.validate(pizza);

        existing.setName(pizza.getName());
        existing.setTopping(pizza.getTopping());
        existing.setCheese(pizza.getCheese());
        existing.setPrice(pizza.getPrice());

        // skapar uppdaterat entitet
        return pizzaRepository.save(existing);


    }

    // tar bort pizza
    public void deletePizza(Long id) {

        // kollar om pizza finns innan jag tar bort den
        Pizza existing = pizzaRepository.findById(id)
                .orElseThrow(() -> new NoPizzaFoundException("Ingen pizza med id" + id));
        pizzaRepository.deleteById(existing.getId());
    }


}




