package ch.springboot.receipe.services.inheritance;

import ch.springboot.receipe.models.inheritance.Vehicle;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);

    Long count();
}
