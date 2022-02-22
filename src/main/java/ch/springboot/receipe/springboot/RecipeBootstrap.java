package ch.springboot.receipe.springboot;

import ch.springboot.receipe.models.Category;
import ch.springboot.receipe.models.Ingredient;
import ch.springboot.receipe.models.Notes;
import ch.springboot.receipe.models.Recipe;
import ch.springboot.receipe.repositories.CategoryRepository;
import ch.springboot.receipe.repositories.RecipeRepository;
import ch.springboot.receipe.repositories.UnitOfMeasureRepository;
import ch.springboot.receipe.utils.Difficulty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(recipeRepository.count() == 0) {
            recipeRepository.saveAll(getRecipes());
            System.out.println("SAVED RECIPES\n=================");
        }
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();
        System.out.println("--- How to Make the Best Guacamole --- ");

        System.out.println("--- Creating recipe... ");
        Recipe guacamole = new Recipe();
        guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chilis, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        guacamole.setCookTime(10);
        guacamole.setServings(4);
        guacamole.setPrepTime(10);
        guacamole.setSource("https://www.simplyrecipes.com/");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setDirections("1. Cut the avocado:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3. Add remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                "4. Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n");
        System.out.println("--- Creating NOTES... ");
        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        guacamole.setNotes(guacamoleNotes);
        guacamoleNotes.setRecipe(guacamole);
        System.out.println("--- Created guacamoleNotes for GUACAMOLE ");

        System.out.println("--- Creating CATEGORIES... ");
        Set<Category> categories = new HashSet<>();
        Category american =  new Category();
        categories.add(categoryRepository.findByDescription("American").get());
        guacamole.setCategories(categories);
        System.out.println("--- Created category AMERICAN for GUACAMOLE");

        System.out.println("--- Creating ingredients... ");
        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient avocado = new Ingredient();
        avocado.setDescription("ripe avocado");
        avocado.setAmount(BigDecimal.valueOf(2));
        avocado.setRecipe(guacamole);
        System.out.println("-- saved avocado");
        ingredients.add(avocado);
        System.out.println("--- Added ingredient:: " + avocado.getDescription());

        Ingredient salt = new Ingredient();
        salt.setDescription("salt, plus more to taste");
        salt.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        salt.setAmount(BigDecimal.valueOf(1 / 4));
        salt.setRecipe(guacamole);
        ingredients.add(salt);
        System.out.println("--- Added ingredient:: " + salt.getDescription());

        Ingredient freshLimeOrLemonJuice = new Ingredient();
        freshLimeOrLemonJuice.setDescription("fresh lime or lemon juice");
        freshLimeOrLemonJuice.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        freshLimeOrLemonJuice.setAmount(BigDecimal.valueOf(3));
        freshLimeOrLemonJuice.setRecipe(guacamole);
        ingredients.add(freshLimeOrLemonJuice);
        System.out.println("--- Added ingredient:: " + freshLimeOrLemonJuice.getDescription());

        Ingredient redOnion = new Ingredient();
        redOnion.setDescription("minced red onion or thinly sliced green onion");
        redOnion.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        redOnion.setAmount(BigDecimal.valueOf(2));
        redOnion.setRecipe(guacamole);
        ingredients.add(redOnion);
        System.out.println("--- Added ingredient:: " + redOnion.getDescription());

        Ingredient serranoChillis = new Ingredient();
        serranoChillis.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
        serranoChillis.setAmount(BigDecimal.valueOf(1));
        serranoChillis.setRecipe(guacamole);
        ingredients.add(serranoChillis);
        System.out.println("--- Added ingredient:: " + serranoChillis.getDescription());

        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
        cilantro.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        cilantro.setAmount(BigDecimal.valueOf(1));
        cilantro.setRecipe(guacamole);
        ingredients.add(cilantro);
        System.out.println("--- Added ingredient:: " + cilantro.getDescription());

        Ingredient blackPepper = new Ingredient();
        blackPepper.setDescription("Pinch freshly ground black pepper");
        blackPepper.setRecipe(guacamole);
        ingredients.add(blackPepper);
        System.out.println("--- Added ingredient:: " + blackPepper.getDescription());

        Ingredient tomato = new Ingredient();
        tomato.setDescription("ripe tomato, chopped (optional)");
        tomato.setAmount(BigDecimal.valueOf(1 / 2));
        tomato.setRecipe(guacamole);
        ingredients.add(tomato);
        System.out.println("--- Added ingredient:: " + tomato.getDescription());

        Ingredient redRadishOrJicama = new Ingredient();
        redRadishOrJicama.setDescription("Red radish or jicama slices for garnish (optional)");
        redRadishOrJicama.setAmount(BigDecimal.valueOf(1 / 2));
        redRadishOrJicama.setRecipe(guacamole);
        ingredients.add(redRadishOrJicama);
        System.out.println("--- Added ingredient:: " + redRadishOrJicama.getDescription());

        Ingredient tortillaChips = new Ingredient();
        tortillaChips.setDescription("Tortilla chips, to serve");
        tortillaChips.setRecipe(guacamole);
        ingredients.add(tortillaChips);
        System.out.println("--- Added ingredient:: " + tortillaChips.getDescription());

        guacamole.setIngredients(ingredients);
        System.out.println(" ---- Added all these ingredients to recipe");

        recipes.add(guacamole);
        return recipes;
    }


}
