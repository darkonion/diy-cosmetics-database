package pl.biologicznieczynny.diycosmeticsdatabase.repositories.projections;

import org.springframework.data.rest.core.config.Projection;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.models.UnitOfMeasure;

@Projection(types = IngredientQuantity.class, name="firstProjection")
public interface IngredientQuantityProjection {

    Long getId();
    int getAmount();
    Ingredient getIngredient();
    UnitOfMeasure getUnitOfMeasure();

}
