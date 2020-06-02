package pl.biologicznieczynny.diycosmeticsdatabase.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Tool extends BaseEntity {

    private String name;

    @Lob
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "tools")
    private Set<Recipe> recipes = new HashSet<>();
}
