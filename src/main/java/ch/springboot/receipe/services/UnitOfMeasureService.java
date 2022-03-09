package ch.springboot.receipe.services;

import ch.springboot.receipe.commands.UnitOfMeasureCommand;
import ch.springboot.receipe.models.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
