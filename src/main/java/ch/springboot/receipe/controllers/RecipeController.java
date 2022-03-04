package ch.springboot.receipe.controllers;

import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.CategoryRepository;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import ch.springboot.receipe.services.RecipeService;
import ch.springboot.receipe.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
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

    @GetMapping("/get/{id}")
    public String getRecipeById(Model model, @PathVariable Long id) throws Exception {
        log.info("--- GET recipe by ID: " + id);

        Optional<Recipe> recipeOptional = Optional.ofNullable(recipeService.getRecipeById(id));
        if(!recipeOptional.isPresent()) {
            throw new Exception("IS NOT FOUND");
        }

        Recipe recipe = recipeOptional.get();
        log.info("Recipe with ID: " + recipe.getId());

        model.addAttribute("recipe", recipe);
        return "/recipes/details";
    }
}
