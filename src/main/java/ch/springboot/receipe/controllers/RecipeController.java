package ch.springboot.receipe.controllers;

import ch.springboot.receipe.repositories.CategoryRepository;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import ch.springboot.receipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.info("Getting Recipe Controller!!");
        model.addAttribute("recipes", recipeService.getRecipies());
        return "/recipes/index";
    }

    @RequestMapping("/details")
    public String getDetails(Model model) {
        model.addAttribute("recipes", recipeService.getRecipies());
        return "/recipes/details";
    }
}
