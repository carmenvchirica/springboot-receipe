package ch.springboot.receipe.controllers;

import ch.springboot.receipe.models.Category;
import ch.springboot.receipe.models.UnitOfMeasure;
import ch.springboot.receipe.repositories.CategoryRepository;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/receipes")
public class ReceipeController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

    public ReceipeController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"", "/"})
    public String getReceipes(Model model) {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> UnitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Spoon");

        System.out.println("category: " + categoryOptional.get().getId());
        System.out.println("unitOfMeasure: " + UnitOfMeasureOptional.get().getId());

        model.addAttribute("recipes", recipeRepository.findAll());
        return "/recipes/index";
    }
}
