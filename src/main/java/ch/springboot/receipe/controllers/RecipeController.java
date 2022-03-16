package ch.springboot.receipe.controllers;

import ch.springboot.receipe.commands.RecipeCommand;
import ch.springboot.receipe.exceptions.NotFoundException;
import ch.springboot.receipe.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private static final String RECIPE_FORM_URL = "recipes/recipeform";
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

        return RECIPE_FORM_URL;
    }

    @RequestMapping("/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_FORM_URL;
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult result) {

        if(result.hasErrors()) {
            log.debug("Errors: ");
            result.getAllErrors().forEach(err -> {
                log.debug(err.toString());
            });
            return RECIPE_FORM_URL;
        }
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


}
