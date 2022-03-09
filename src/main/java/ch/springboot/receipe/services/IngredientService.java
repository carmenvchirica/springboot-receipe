package ch.springboot.receipe.services;

import ch.springboot.receipe.commands.IngredientCommand;
import ch.springboot.receipe.commands.RecipeCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveRecipeCommand(IngredientCommand command);

    IngredientCommand findCommandById(Long l);
}
