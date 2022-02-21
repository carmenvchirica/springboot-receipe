package ch.springboot.receipe.springboot;

import ch.springboot.receipe.models.Ingredient;
import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import ch.springboot.receipe.utils.Difficulty;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class SpringBootCommandLineRunner implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public SpringBootCommandLineRunner(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(recipeRepository.count() == 0) {
            System.out.println("--- How to Make the Best Guacamole --- ");
            Recipe guacamole = new Recipe();
            guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chilis, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
            guacamole.setCookTime(10);
            guacamole.setServings(4);
            guacamole.setPrepTime(10);
            guacamole.setSource("https://www.simplyrecipes.com/");
            guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
            guacamole.setDifficulty(Difficulty.EASY);

            Set<Ingredient> ingredients = new HashSet<>();

            Ingredient avocado = new Ingredient();
            avocado.setDescription("ripe avocado");
            avocado.setAmount(BigDecimal.valueOf(2));
            avocado.setRecipe(guacamole);
            // ingredientRepository.save(avocado);
            System.out.println("-- saved avocado");
            ingredients.add(avocado);

            Ingredient salt = new Ingredient();
            salt.setDescription("salt, plus more to taste");
            salt.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").get());
            salt.setAmount(BigDecimal.valueOf(1 / 4));
            salt.setRecipe(guacamole);
            ingredients.add(salt);

            Ingredient freshLimeOrLemonJuice = new Ingredient();
            freshLimeOrLemonJuice.setDescription("fresh lime or lemon juice");
            freshLimeOrLemonJuice.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
            freshLimeOrLemonJuice.setAmount(BigDecimal.valueOf(3));
            freshLimeOrLemonJuice.setRecipe(guacamole);
            ingredients.add(freshLimeOrLemonJuice);

            Ingredient redOnion = new Ingredient();
            redOnion.setDescription("minced red onion or thinly sliced green onion");
            redOnion.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
            redOnion.setAmount(BigDecimal.valueOf(2));
            redOnion.setRecipe(guacamole);
            ingredients.add(redOnion);

            Ingredient serranoChillis = new Ingredient();
            serranoChillis.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
            serranoChillis.setAmount(BigDecimal.valueOf(1));
            serranoChillis.setRecipe(guacamole);
            ingredients.add(serranoChillis);

            Ingredient cilantro = new Ingredient();
            cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
            cilantro.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
            cilantro.setAmount(BigDecimal.valueOf(1));
            cilantro.setRecipe(guacamole);
            ingredients.add(cilantro);

            Ingredient blackPepper = new Ingredient();
            blackPepper.setDescription("Pinch freshly ground black pepper");
            blackPepper.setRecipe(guacamole);
            ingredients.add(blackPepper);

            Ingredient tomato = new Ingredient();
            tomato.setDescription("ripe tomato, chopped (optional)");
            tomato.setAmount(BigDecimal.valueOf(1 / 2));
            tomato.setRecipe(guacamole);
            ingredients.add(tomato);

            Ingredient redRadishOrJicama = new Ingredient();
            redRadishOrJicama.setDescription("Red radish or jicama slices for garnish (optional)");
            redRadishOrJicama.setAmount(BigDecimal.valueOf(1 / 2));
            redRadishOrJicama.setRecipe(guacamole);
            ingredients.add(redRadishOrJicama);

            Ingredient tortillaChips = new Ingredient();
            tortillaChips.setDescription("Tortilla chips, to serve");
            tortillaChips.setRecipe(guacamole);
            ingredients.add(tortillaChips);

            guacamole.setIngredients(ingredients);
            recipeRepository.save(guacamole);
        }
    }
}
