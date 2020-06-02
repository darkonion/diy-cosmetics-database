package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.models.RecipeCategory;


public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Long> {
}
