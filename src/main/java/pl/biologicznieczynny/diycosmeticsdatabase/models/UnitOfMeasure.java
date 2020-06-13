package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UnitOfMeasure extends BaseEntity {
    private String uom;
}
