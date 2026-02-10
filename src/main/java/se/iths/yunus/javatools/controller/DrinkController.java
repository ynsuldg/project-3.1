package se.iths.yunus.javatools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String getAllMovies(Model model) {
        model.addAttribute("drinks", drinkService.findAll());
        return "drinks";
    }

}
