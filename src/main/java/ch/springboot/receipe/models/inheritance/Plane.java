package ch.springboot.receipe.models.inheritance;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Plane extends Vehicle implements Flyable, Movable{

    private BigDecimal propellerSize;

    public BigDecimal getPropellerSize() {
        return propellerSize;
    }

    @Override
    public void isFlying() {
        System.out.println("The plane fly!");
    }

    @Override
    public void isMoving() {
        System.out.println("The plane moves!");
    }

    public void setPropellerSize(BigDecimal propellerSize) {
        this.propellerSize = propellerSize;
    }
}
