package ch.springboot.receipe.enums;

public enum UnitOfMeasureEnum {
    TEASPOON("Teaspoon"),
    CUP ("Cup");

    private final String name;

    UnitOfMeasureEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
