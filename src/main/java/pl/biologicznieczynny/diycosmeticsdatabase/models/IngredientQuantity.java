package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class IngredientQuantity extends BaseEntity {

    private int amount;

    @OneToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    private Recipe recipe;

}
