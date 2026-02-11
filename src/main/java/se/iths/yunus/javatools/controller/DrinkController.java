package se.iths.yunus.javatools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.yunus.javatools.model.Drink;
import se.iths.yunus.javatools.service.DrinkService;

@Controller
@RequestMapping("/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public String getAllDrinks(Model model) {
        model.addAttribute("drinks", drinkService.getAllDrinks());
        return "drinks";
    }

    @GetMapping("/{id}")
    public String getDrink(@PathVariable Long id, Model model) {
        model.addAttribute("drink", drinkService.getDrink(id));
        return "drinks";
    }

    @PostMapping
    public String postNewDrink(@ModelAttribute Drink drink) {
        Drink drink2 = drinkService.postNewDrink(drink);
        return "redirect:/drinks";
    }

    @PutMapping("/{id}")
    public String putExistingDrink(@PathVariable Long id, @ModelAttribute Drink drink) {
        Drink drink2 = drinkService.putExistingDrink(id, drink);
        return "redirect:/drinks";
    }

    @DeleteMapping("/{id}")
    public String deleteDrink(@PathVariable Long id) {
        drinkService.deleteDrink(id);
        return "redirect:/drinks";
    }
}