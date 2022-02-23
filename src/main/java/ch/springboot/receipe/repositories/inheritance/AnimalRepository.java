package ch.springboot.receipe.repositories.inheritance;

import ch.springboot.receipe.models.inheritance.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
