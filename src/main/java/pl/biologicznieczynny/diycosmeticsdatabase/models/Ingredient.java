package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
public class Ingredient extends BaseEntity {

    private String name;
    private String latinName;
    private String description;
    private String blogUrl;
    private String imageUrl;

    @UpdateTimestamp
    private LocalDate dateUpdated;
}
