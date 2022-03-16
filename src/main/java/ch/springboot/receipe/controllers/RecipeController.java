package ch.springboot.receipe.controllers;

import ch.springboot.receipe.commands.RecipeCommand;
import ch.springboot.receipe.exceptions.NotFoundException;
import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;

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
    public String getRecipeById(Model model, @PathVariable String id) throws Exception {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {
        log.error("Handling not found exception");
        ModelAndView maw = new ModelAndView("recipes/exceptions/404error");
        maw.addObject("exception", exception);
        return maw;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception) {
        log.error("Handling bad request");
        ModelAndView maw = new ModelAndView("recipes/exceptions/400error");
        maw.addObject("exception", exception);
        return maw;
    }
}
