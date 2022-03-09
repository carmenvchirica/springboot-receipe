package ch.springboot.receipe.services;

import ch.springboot.receipe.commands.RecipeCommand;
import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipies();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long l);

    void deleteById(Long id);
}
