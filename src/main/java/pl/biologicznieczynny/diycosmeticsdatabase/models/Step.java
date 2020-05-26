package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Step extends BaseEntity {

    private String name;

    @Lob
    private String detail;

    @ManyToOne
    private Recipe recipe;
}
