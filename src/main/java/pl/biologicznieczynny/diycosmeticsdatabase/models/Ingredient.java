package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDate;

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

    @UpdateTimestamp
    private LocalDate dateUpdated;
}
