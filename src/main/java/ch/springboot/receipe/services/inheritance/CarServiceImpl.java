package ch.springboot.receipe.services.inheritance;

import ch.springboot.receipe.models.inheritance.Vehicle;
import ch.springboot.receipe.repositories.inheritance.VehicleRepository;

public class CarServiceImpl extends VehicleServiceImpl {

    public CarServiceImpl(VehicleRepository vehicleRepository) {
        super(vehicleRepository);
    }

}
