package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.projections.IngredientQuantityProjection;

@RepositoryRestResource(excerptProjection = IngredientQuantityProjection.class)
public interface IngredientQuantityRepository extends JpaRepository<IngredientQuantity, Long> {

    Page<IngredientQuantity> findByRecipeId(@RequestParam("id") Long id, Pageable pageable);
}
