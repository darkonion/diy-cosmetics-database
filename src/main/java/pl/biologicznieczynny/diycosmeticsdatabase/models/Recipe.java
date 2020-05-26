package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe extends BaseEntity {

    private String name;

    @Lob
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    private int prepTime;

    @CreationTimestamp
    private LocalDate dateCreated;

    @UpdateTimestamp
    private LocalDate dateUpdated;

//    @Lob
//    private byte[] image;

    @OneToMany(cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST}, mappedBy = "recipe")
    private Set<Tool> equipment = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_recipeCategory",
            joinColumns = @JoinColumn(name ="recipe_id"),
            inverseJoinColumns = @JoinColumn(name ="category_id"))
    private Set<RecipeCategory> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Step> steps = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<IngredientQuantity> ingredients = new HashSet<>();


}
