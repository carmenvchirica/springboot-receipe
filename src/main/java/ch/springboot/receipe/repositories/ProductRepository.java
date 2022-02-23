package ch.springboot.receipe.repositories;

import ch.springboot.receipe.models.inheritrance.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
