package ch.springboot.receipe.repositories;

import ch.springboot.receipe.enums.UnitOfMeasureEnum;
import ch.springboot.receipe.models.UnitOfMeasure;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void findByDescriptionTeaspoon() throws Exception {
        String unitOfMeasureDescription = UnitOfMeasureEnum.TEASPOON.getName();
        boolean isPresent = unitOfMeasureRepository.findByDescription(unitOfMeasureDescription).isPresent();
        if(isPresent) {
            Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(unitOfMeasureDescription);
            assertEquals(unitOfMeasureDescription, unitOfMeasureOptional.get().getDescription());
        } else {
            throw new Exception("No unit of measure present: " +  unitOfMeasureDescription);
        }
    }

    @Test
    public void findByDescriptionCup() throws Exception {
        String unitOfMeasureDescription = UnitOfMeasureEnum.CUP.getName();
        boolean isPresent = unitOfMeasureRepository.findByDescription(unitOfMeasureDescription).isPresent();
        if(isPresent) {
            Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(unitOfMeasureDescription);
            assertEquals(unitOfMeasureDescription, unitOfMeasureOptional.get().getDescription());
        } else {
            throw new Exception("No unit of measure present: " +  unitOfMeasureDescription);
        }
    }
}