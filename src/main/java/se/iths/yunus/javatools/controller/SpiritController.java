package se.iths.yunus.javatools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.yunus.javatools.model.Spirit;
import se.iths.yunus.javatools.service.SpiritService;

@Controller
@RequestMapping("/spirits")
public class SpiritController {
    private final SpiritService spiritService;

    public SpiritController(SpiritService spiritService) {
        this.spiritService = spiritService;
    }

    @GetMapping
    public String getAllSpirits(Model model) {
        model.addAttribute("spirits", spiritService.getAllSpirit());
        return "spirits";
    }

    @GetMapping("/new")
    public String showCreateFormSpirit() {
        return "create-spirit";
    }

    @PostMapping
    public String createSpirits(@ModelAttribute Spirit spirit) {
        Spirit spirit1 = spiritService.createSpirit(spirit);
        return "redirect:/spirits";
    }

    @GetMapping("/{id}")
    public String getSpirit(@PathVariable Long id, Model model) {
        model.addAttribute("spirit", spiritService.getSpiritId(id));
        return "spirit";
    }

    @PutMapping("/{id}")
    public String updateSpirit(@PathVariable Long id, @ModelAttribute Spirit spirit) {
        Spirit spirit1 = spiritService.updateSpirit(id, spirit);
        return "redirect:/spirits";
    }

    @GetMapping("/{id}/edit")
    public String showUpdate(@PathVariable Long id, Model model) {
        Spirit spirit = spiritService.getSpiritId(id);
        model.addAttribute("spirit", spirit);
        return "edit-spirit";
    }

    @DeleteMapping("/{id}")
    public String deleteSpirit(@PathVariable Long id) {
        spiritService.deleteSpirit(id);
        return "redirect:/spirits";
    }
}
