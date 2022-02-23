package ch.springboot.receipe.services.inheritance;

import ch.springboot.receipe.models.inheritance.Vehicle;
import ch.springboot.receipe.repositories.inheritance.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Long count() {
        return vehicleRepository.count();
    }
}
