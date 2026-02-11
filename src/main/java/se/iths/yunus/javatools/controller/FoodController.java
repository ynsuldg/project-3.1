package se.iths.yunus.javatools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.yunus.javatools.model.Food;
import se.iths.yunus.javatools.service.FoodService;

@Controller
@RequestMapping("/foods")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public String listAllFoods(Model model) {
        model.addAttribute("foods", foodService.findAll());
        return"foods/list";
    }

    @GetMapping("/{id}")
    public String showFood(@PathVariable Long id, Model model) {
        model.addAttribute("food", foodService.findById(id));
        return "foods/show";
    }

    @PostMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("food", new Food());
        return "foods/create";
    }

    @GetMapping("/create")
    public String createFood(@ModelAttribute Food food) {
        foodService.create(food);
        return "redirect:/foods";
    }



}
