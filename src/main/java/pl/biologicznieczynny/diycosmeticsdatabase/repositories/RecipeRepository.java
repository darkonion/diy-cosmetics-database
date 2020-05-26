package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
