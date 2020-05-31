package pl.biologicznieczynny.diycosmeticsdatabase.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "recipe")
public class Tool extends BaseEntity {

    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "tools")
    private Set<Recipe> recipes = new HashSet<>();
}
