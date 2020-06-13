package pl.biologicznieczynny.diycosmeticsdatabase.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"}, callSuper = true)
@NoArgsConstructor
@Entity
public class IngredientQuantity extends BaseEntity {

    private int amount;

    @OneToOne(fetch = FetchType.EAGER)
    private Ingredient ingredient;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    @ManyToMany
    @JoinTable(name = "ingredient_quantity_ingredient",
            joinColumns = @JoinColumn(name ="ingredient_quantity_id"),
            inverseJoinColumns = @JoinColumn(name ="ingredient_id"))
    private Set<Ingredient> replacements = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
