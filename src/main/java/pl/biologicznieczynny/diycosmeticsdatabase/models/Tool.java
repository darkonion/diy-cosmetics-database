package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "recipe")
public class Tool extends BaseEntity {

    private String name;
    private String description;

    @ManyToOne
    private Recipe recipe;
}
