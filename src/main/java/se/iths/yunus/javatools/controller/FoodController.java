package se.iths.yunus.javatools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.yunus.javatools.model.Food;
import se.iths.yunus.javatools.service.FoodService;


@Controller
@RequestMapping("/foods")
public class FoodController {
    private final FoodService foodService;
    private static final Logger log = LoggerFactory.getLogger(FoodController.class);

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // ===================== READ ================================
    @GetMapping
    public String listAllFoods(Model model) {
        log.info("GET /foods -listning all foods.");
        model.addAttribute("foods", foodService.findAll());
        return"list-food";
    }

    @GetMapping("/{id}")
    public String showFood(@PathVariable Long id, Model model) {
        log.info("GET /foods/{} - showing food details.", id);
        try {
            model.addAttribute("food", foodService.findById(id));
            return "show-food";
        } catch(RuntimeException ex){
            log.warn("Food with id {} not found", id);
            model.addAttribute("errorMessage", ex.getMessage());
            return "show-food";
        }
    }
    // ======================= CREATE ============================
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        log.info("GET /foods/create - showing create form.");
        model.addAttribute("food", new Food());
        return "create-food";
    }

    @PostMapping("/create")
    public String createFood( @ModelAttribute("food") Food food, Model model) {
        log.info("Post /foods/create - creating new food: {}", food.getName());
        try {
            foodService.create(food);
        } catch (RuntimeException ex){
            log.warn(" POST /foods/create failed: {}", ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());
            return "create-food";
        }

        return "redirect:/foods";
    }
    // =========================UPDATE ===================================
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        log.info("GET /foods/edit/{} -showing edit form.", id);
        try {
            model.addAttribute("food", foodService.findById(id));
            return "edit-food";
        } catch (RuntimeException ex) {
            log.warn("GET /foods/edit/{} failed: {}", id, ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());
            return "show-food";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateFood(@PathVariable Long id, @ModelAttribute Food updated, Model model){
        log.info("POST /foods/edit/{} - updating food.",id);
        try {
            foodService.update(id, updated);
            } catch (RuntimeException ex) {
            log.warn("POST /foods/edit/{} failed: {}", id, ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());
            return "edit-food";
            }

        return "redirect:/foods";
    }

    // =============================DELETE ====================================
    @PostMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        log.info("POST /foods/delete/{} -deleting food.", id);
        foodService.delete(id);
        return "redirect:/foods";
    }



}
