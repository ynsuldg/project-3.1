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

    @GetMapping({"", "/"})
    public String getAllSpirits(Model model) {
        model.addAttribute("spirits", spiritService.getAllSpirit());
        return "spirits";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("spirit", new Spirit());
        return "create-spirit";
    }

    @PostMapping
    public String createSpirit(@ModelAttribute Spirit spirit) {
        spiritService.createSpirit(spirit);
        return "redirect:/spirits";
    }

    @GetMapping("/{id}")
    public String getSpirit(@PathVariable Long id, Model model) {
        model.addAttribute("spirit", spiritService.getSpiritId(id));
        return "spirit";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("spirit", spiritService.getSpiritId(id));
        return "edit-spirit";
    }

    @PostMapping("/{id}")
    public String updateSpirit(@PathVariable Long id, @ModelAttribute Spirit spirit) {
        spiritService.updateSpirit(id, spirit);
        return "redirect:/spirits";
    }

    @PostMapping("/{id}/delete")
    public String deleteSpirit(@PathVariable Long id) {
        spiritService.deleteSpirit(id);
        return "redirect:/spirits";
    }
}