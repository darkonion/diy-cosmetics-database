package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class UnitOfMeasure extends BaseEntity {
    private String uom;
}
