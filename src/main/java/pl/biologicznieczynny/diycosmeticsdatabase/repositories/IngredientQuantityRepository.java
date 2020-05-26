package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;

@Repository
public interface IngredientQuantityRepository extends JpaRepository<IngredientQuantity, Long> {
}
