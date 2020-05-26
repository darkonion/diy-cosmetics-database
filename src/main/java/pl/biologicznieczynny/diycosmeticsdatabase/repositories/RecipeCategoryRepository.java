package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.RecipeCategory;

@Repository
public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {
}
