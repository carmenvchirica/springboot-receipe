package ch.springboot.receipe.repositories;

import ch.springboot.receipe.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;


public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
