package ch.springboot.receipe.repositories;

import ch.springboot.receipe.models.inheritrance.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
}
