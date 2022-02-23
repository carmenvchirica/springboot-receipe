package ch.springboot.receipe.services.inheritance;

import ch.springboot.receipe.models.inheritance.Vehicle;
import ch.springboot.receipe.repositories.inheritance.VehicleRepository;

public class PlaneServiceImpl extends VehicleServiceImpl {

    public PlaneServiceImpl(VehicleRepository vehicleRepository) {
        super(vehicleRepository);
    }

}
