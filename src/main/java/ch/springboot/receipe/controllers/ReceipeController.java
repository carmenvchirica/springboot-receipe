package ch.springboot.receipe.controllers;

import ch.springboot.receipe.repositories.CategoryRepository;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import ch.springboot.receipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/receipes")
public class ReceipeController {

    private final RecipeService recipeService;

    public ReceipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/"})
    public String getReceipes(Model model) {
        model.addAttribute("recipes", recipeService.getRecipies());
        return "/recipes/index";
    }
}
