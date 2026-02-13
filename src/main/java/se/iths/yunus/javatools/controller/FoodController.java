package se.iths.yunus.javatools.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    // ===================== READ ================================
    @GetMapping
    public String listAllFoods(Model model) {
        model.addAttribute("foods", foodService.findAll());
        return"list-food";
    }

    @GetMapping("/{id}")
    public String showFood(@PathVariable Long id, Model model) {
        model.addAttribute("food", foodService.findById(id));
        return "show-food";
    }
    // ======================= CREATE ============================
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("food", new Food());
        return "create-food";
    }

    @PostMapping("/create")
    public String createFood( @ModelAttribute("food") Food food, Model model) {
        try {
            foodService.create(food);
        } catch (RuntimeException ex){
            model.addAttribute("errorMessage", ex.getMessage());
            return "create-food";
        }

        return "redirect:/foods";
    }
    // =========================UPDATE ===================================
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("food", foodService.findById(id));
            return "edit-food";
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "show-food";
        }
    }

    @PostMapping("edit/{id}")
    public String updateFood(@PathVariable Long id, @ModelAttribute Food updated, Model model){
        try {
            foodService.update(id, updated);
            } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "edit-food";
            }

        return "redirect:/foods/";
    }

    // =============================DELETE ====================================
    @PostMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.delete(id);
        return "redirect:/foods";
    }



}
