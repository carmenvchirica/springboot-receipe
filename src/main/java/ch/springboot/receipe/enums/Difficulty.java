package ch.springboot.receipe.enums;

import lombok.Getter;

@Getter
public enum Difficulty {
    EASY("Easy"),
    MODERATE("Moderate"),
    KIND_OF_HARD("Kind of hard"),
    HARD("Hard");

    private final String name;

    private Difficulty(String name) {
        this.name = name;
    }

}
