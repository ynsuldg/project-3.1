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

    // ===================== READ ================================
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
    // ======================= CREATE ============================
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
    // =========================UPDATE ===================================
    @GetMapping("/eidt/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("food", foodService.findById(id));
        return "foods/edit";
    }

    @PostMapping("edit/{id}")
    public String updateFood(@PathVariable Long id, @ModelAttribute Food updated){
        foodService.update(id, updated);
        return "redirect:/foods/" + id;
    }

    // =============================DELETE ====================================
    @PostMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.delete(id);
        return "redirect:/foods";
    }



}
