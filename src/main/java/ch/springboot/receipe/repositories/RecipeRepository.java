package ch.springboot.receipe.repositories;

import ch.springboot.receipe.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
