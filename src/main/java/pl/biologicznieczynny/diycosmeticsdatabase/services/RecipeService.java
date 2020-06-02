package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;

import java.net.URI;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {this.recipeRepository = recipeRepository;}


    public Page<Recipe> findAll(int page, int size, Long categoryId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        if (categoryId == null) {
            return recipeRepository.findAll(pageable);
        } else {
            return recipeRepository.findByRecipeCategoriesId(categoryId, pageable);
        }
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe with id: " + id + " not found"));
    }

    public List<Recipe> findBySearchQuery(String query) {
        query.toLowerCase().trim();
        return recipeRepository.findByNameContainingIgnoreCase(query);
    }

    public ResponseEntity<Recipe> addNewRecipe(Recipe recipe) {

        recipe.getIngredientQuantities().forEach(q -> q.setRecipe(recipe));
        recipe.getSteps().forEach(s -> s.setRecipe(recipe));

        Recipe savedRecipe = recipeRepository.save(recipe);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRecipe.getId())
                .toUri();

        ResponseEntity<Recipe> response = ResponseEntity
                .created(uri)
                .body(savedRecipe);

        return response;
    }
}
