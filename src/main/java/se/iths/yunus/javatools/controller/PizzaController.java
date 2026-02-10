package se.iths.yunus.javatools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.yunus.javatools.service.PizzaService;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {


    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public String getAllPizzas(Model model) {
        model.addAttribute("pizzas", pizzaService.getAllPizza());
        return "pizzas";

    }
}
