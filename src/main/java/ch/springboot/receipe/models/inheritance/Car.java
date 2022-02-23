package ch.springboot.receipe.models.inheritance;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle  implements Movable {

    private Integer doorsNo;

    @Override
    public void isMoving() {
        System.out.println("The car moves!");
    }

    public Integer getDoorsNo() {
        return doorsNo;
    }

    public void setDoorsNo(Integer doorsNo) {
        this.doorsNo = doorsNo;
    }
}
