package ch.springboot.receipe.models.inheritrance;

import javax.persistence.Entity;

@Entity
public class Pen extends Product{

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
