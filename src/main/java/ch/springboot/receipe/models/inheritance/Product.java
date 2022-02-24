package ch.springboot.receipe.models.inheritance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type", discriminatorType = DiscriminatorType.INTEGER)
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long productId;
    private String name;
}
