package ch.springboot.receipe.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
    }

    public Category(String description) {
        this.description = description;
    }

    public void addRecipe(Recipe recipe) {
        recipe.addCategory(this);
        recipes.add(recipe);
    }
}
