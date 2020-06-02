package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
