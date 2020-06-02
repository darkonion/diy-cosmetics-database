package pl.biologicznieczynny.diycosmeticsdatabase.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IngredientQuantity extends BaseEntity {

    private int amount;

    @OneToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
