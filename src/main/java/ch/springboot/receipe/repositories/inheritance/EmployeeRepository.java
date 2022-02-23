package ch.springboot.receipe.repositories.inheritance;

import ch.springboot.receipe.models.inheritance.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
