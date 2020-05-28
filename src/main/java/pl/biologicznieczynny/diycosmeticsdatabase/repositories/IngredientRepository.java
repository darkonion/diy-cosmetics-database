package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;


@CrossOrigin
@RepositoryRestResource(exported = false)
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
