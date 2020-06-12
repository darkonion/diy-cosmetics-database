package pl.biologicznieczynny.diycosmeticsdatabase.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "replacements")
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient extends BaseEntity {

    private String name;
    private String latinName;

    @Lob
    private String description;
    private String blogUrl;
    private String imageUrl;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Ingredient> replacements = new HashSet<>();

    @UpdateTimestamp
    private LocalDate dateUpdated;
}
