package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;


@CrossOrigin
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
