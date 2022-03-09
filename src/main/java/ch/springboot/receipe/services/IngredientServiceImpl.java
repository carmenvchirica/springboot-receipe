package ch.springboot.receipe.services;

import ch.springboot.receipe.commands.IngredientCommand;
import ch.springboot.receipe.converters.IngredientCommandToIngredient;
import ch.springboot.receipe.converters.IngredientToIngredientCommand;
import ch.springboot.receipe.models.Ingredient;
import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                IngredientCommandToIngredient ingredientCommandToIngredient,
                                RecipeRepository recipeRepository,
                                UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveRecipeCommand(IngredientCommand command) {
       Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

       if(recipeOptional.isPresent()) {
           // update Ingredient
           Recipe recipe = recipeOptional.get();

           Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();
           if(ingredientOptional.isPresent()) {
               Ingredient ingredientFound = ingredientOptional.get();
               ingredientFound.setDescription(command.getDescription());
               ingredientFound.setAmount(command.getAmount());
               ingredientFound.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
                       .orElseThrow(()-> new RuntimeException("UOM NOT FOUND!")));
           } else {
               // add new Ingredient
               recipe.addIngredient(ingredientCommandToIngredient.convert(command));
           }

           Recipe savedRecipe = recipeRepository.save(recipe);

           // check for fail
           if (savedRecipe != null && savedRecipe.getId() != null) {
               return ingredientToIngredientCommand.convert(savedRecipe.getIngredients()
                       .stream().filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                       .findFirst().get());
           }
       }
        return new IngredientCommand();
    }

    @Override
    public IngredientCommand findCommandById(Long l) {
        return null;
    }
}
