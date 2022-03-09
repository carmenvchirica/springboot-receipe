package ch.springboot.receipe.controllers;

import ch.springboot.receipe.commands.RecipeCommand;
import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.CategoryRepository;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import ch.springboot.receipe.services.RecipeService;
import ch.springboot.receipe.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        Optional<Recipe> recipeOptional = Optional.ofNullable(recipeService.findById(id));
        if(!recipeOptional.isPresent()) {
            throw new Exception("IS NOT FOUND INTO DB");
        }

        Recipe recipe = recipeOptional.get();
        log.info("Recipe with ID: " + recipe.getId());

        model.addAttribute("recipe", recipe);
        return "/recipes/details";
    }

    @GetMapping("/get/{id}/update")
    public String updateRecipe(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipes/recipeform";
    }

    @RequestMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipes/recipeform";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);

        return "redirect:/recipes/get/" + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipes";
    }
}
