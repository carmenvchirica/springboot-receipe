package ch.springboot.receipe.springboot;

import ch.springboot.receipe.models.*;
import ch.springboot.receipe.models.inheritance.*;
import ch.springboot.receipe.repositories.*;
import ch.springboot.receipe.repositories.inheritance.AnimalRepository;
import ch.springboot.receipe.repositories.inheritance.EmployeeRepository;
import ch.springboot.receipe.repositories.inheritance.ProductRepository;
import ch.springboot.receipe.services.inheritance.VehicleServiceImpl;
import ch.springboot.receipe.utils.Difficulty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final AnimalRepository animalRepository;
    private final VehicleServiceImpl vehicleServiceImpl;

    public RecipeBootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                           CategoryRepository categoryRepository, EmployeeRepository employeeRepository, ProductRepository productRepository,
                           AnimalRepository animalRepository, VehicleServiceImpl vehicleServiceImpl) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.animalRepository = animalRepository;
        this.vehicleServiceImpl = vehicleServiceImpl;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(recipeRepository != null && recipeRepository.count() == 0) {
            recipeRepository.saveAll(getRecipes());
        }

        if(employeeRepository.count() == 0) {
            savePersons();
        }

        if(productRepository.count() == 0) {
            saveProducts();
        }

        if(animalRepository.count() == 0) {
            saveAnimals();
        }

        if(vehicleServiceImpl.count() == 0) {
            saveVehicles();
        }
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>();
        System.out.println("--- How to Make the Best Guacamole --- ");
        Recipe guacamole = new Recipe();
        guacamole.setName("Guacamole");
        guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chilis, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        guacamole.setCookTime(10);
        guacamole.setServings(4);
        guacamole.setPrepTime(10);
        guacamole.setSource("www.simplyrecipes.com");
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

        guacamole.setNotes(new Notes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, " +
                "wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards."));

        try {
            Category americanCategory = categoryRepository.findByDescription("American").get();
            guacamole.addCategory(americanCategory);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Can not found Category: American");
        }

        UnitOfMeasure eachUom = null;
        try {
            eachUom = unitOfMeasureRepository.findByDescription("EachUom").get();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Can not found Unit Of Measure: EachUom");
        }
        UnitOfMeasure teaspoon = null;
        try {
            teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Can not found Unit Of Measure: Teaspoon");
        }
        UnitOfMeasure tablespoon = null;
        try {
            tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Can not found Unit Of Measure: Tablespoon");
        }

        guacamole.addIngredient(new Ingredient("ripe avocado", BigDecimal.valueOf(2), eachUom));
        guacamole.addIngredient(new Ingredient("salt, plus more to taste", BigDecimal.valueOf(1 / 4), teaspoon));
        guacamole.addIngredient(new Ingredient("fresh lime or lemon juice", BigDecimal.valueOf(3), tablespoon));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(2), tablespoon));
        guacamole.addIngredient(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", BigDecimal.valueOf(1), eachUom));
        guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(1), eachUom));
        guacamole.addIngredient(new Ingredient("Pinch freshly ground black pepper", BigDecimal.valueOf(1), eachUom));
        guacamole.addIngredient(new Ingredient("ripe tomato, chopped (optional)", BigDecimal.valueOf(1/2), eachUom));
        guacamole.addIngredient(new Ingredient("Red radish or jicama slices for garnish (optional)", BigDecimal.valueOf(1/2), eachUom));
        guacamole.addIngredient(new Ingredient("Tortilla chips, to serve", BigDecimal.valueOf(1), eachUom));

        recipes.add(guacamole);
        return recipes;
    }

    private void savePersons() {
        Employee mariana = new Employee();
        mariana.setName("Mariana Popescu");

        Employee gina = new Employee();
        gina.setName("Gina Georgescu");
        gina.setCompany("GSS");

        Employee adina = new Employee();
        adina.setName("Adina Moraru");
        adina.setCompany("UiPath");

        employeeRepository.save(mariana);
        employeeRepository.save(gina);
        employeeRepository.save(adina);
    }

    private void saveProducts() {
        Book book = new Book();
        book.setName("Mindset");
        book.setAuthor("Carol D.");

        Pen pen = new Pen();
        pen.setName("Pix");
        pen.setColor("Red");

        Pen pen2 = new Pen();
        pen2.setName("Stilo");
        pen2.setColor("Blue");

        productRepository.save(book);
        productRepository.save(pen);
        productRepository.save(pen2);
    }

    private void saveAnimals() {
        Animal animal = new Animal();
        animal.setSpecies("Amur Leopard");

        Pet pet = new Pet();
        pet.setName("Dummy");
        pet.setSpecies("Dog");

        animalRepository.save(animal);
        animalRepository.save(pet);
    }

    private void saveVehicles() {
        Vehicle vehicle = new Vehicle();
        vehicle.setManufacturer("vehicle manufacturer");

        Plane beoing = new Plane();
        beoing.setPropellerSize(BigDecimal.valueOf(25.42));
        beoing.setManufacturer("beoing manufacturer");

        beoing.isFlying();
        beoing.isMoving();

        Car logan = new Car();
        logan.setManufacturer("logan manufacturer");
        logan.setDoorsNo(4);

        logan.isMoving();

        vehicleServiceImpl.save(vehicle);
        vehicleServiceImpl.save(beoing);
        vehicleServiceImpl.save(logan);
    }

}
