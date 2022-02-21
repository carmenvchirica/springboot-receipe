package ch.springboot.receipe.repositories;

import ch.springboot.receipe.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
