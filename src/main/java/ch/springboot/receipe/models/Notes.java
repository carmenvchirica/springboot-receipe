package ch.springboot.receipe.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "notes")
    private Recipe recipe;

    @Lob
    private String recipeNotes;

    public Notes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
