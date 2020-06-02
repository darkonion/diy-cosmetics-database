package pl.biologicznieczynny.diycosmeticsdatabase.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Difficulty;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;

@Repository
@CrossOrigin
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByRecipeCategoriesId(@RequestParam("id") Long id, Pageable pageable);
    Page<Recipe> findByDifficulty(@RequestParam("lvl") Difficulty lvl, Pageable pageable);
    Page<Recipe> findByNameContainingIgnoreCase(@RequestParam("query") String query, Pageable pageable);
}
