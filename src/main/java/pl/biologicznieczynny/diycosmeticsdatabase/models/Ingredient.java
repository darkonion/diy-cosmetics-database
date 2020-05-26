package pl.biologicznieczynny.diycosmeticsdatabase.models;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Ingredient extends BaseEntity {

    private String name;
    private String description;
    private String blogUrl;
}
