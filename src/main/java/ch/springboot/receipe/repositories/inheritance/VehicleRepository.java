package ch.springboot.receipe.repositories.inheritance;

import ch.springboot.receipe.models.inheritance.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
