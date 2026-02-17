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

    // LIST ALL
    @GetMapping
    public String getAllDrinks(Model model) {
        model.addAttribute("drinks", drinkService.getAllDrinks());
        return "drink-findall";
    }

    // SHOW ONE
    @GetMapping("/{id}")
    public String getDrink(@PathVariable Long id, Model model) {
        model.addAttribute("drink", drinkService.getDrink(id));
        return "drink-findbyid";
    }

    // HANDLE FORM SUBMIT FROM INDEX PAGE
    @GetMapping("/findbyid")
    public String findById(@RequestParam Long id) {
        // Redirect to /drinks/{id}
        return "redirect:/drinks/" + id;
    }

    // SHOW CREATE FORM
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("drink", new Drink());
        return "drink-post";
    }

    // CREATE
    @PostMapping
    public String postNewDrink(@ModelAttribute Drink drink) {
        drinkService.postNewDrink(drink);
        return "redirect:/drinks";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("drink", drinkService.getDrink(id));
        return "drink-put";
    }

    // UPDATE
    @PutMapping("/{id}")
    public String putExistingDrink(@PathVariable Long id, @ModelAttribute Drink drink) {
        drinkService.putExistingDrink(id, drink);
        return "redirect:/drinks";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteDrink(@PathVariable Long id) {
        drinkService.deleteDrink(id);
        return "redirect:/drinks";
    }
}