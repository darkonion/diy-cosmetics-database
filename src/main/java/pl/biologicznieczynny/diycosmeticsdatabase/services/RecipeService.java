package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {this.recipeRepository = recipeRepository;}


    //getting list of recipes in pageable form
    public Page<Recipe> findAll(int page, int size, Long categoryId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        if (categoryId == null) {
            return recipeRepository.findAll(pageable);
        } else {
            return recipeRepository.findByRecipeCategoriesId(categoryId, pageable);
        }
    }

    //getting recipe by recipe id
    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe with id: " + id + " not found"));
    }

    //search query processing
    public List<Recipe> findBySearchQuery(String query) {
        query.toLowerCase().trim();
        return recipeRepository.findByNameContainingIgnoreCase(query);
    }

    //adding new recipe
    public Recipe addNewRecipe(Recipe recipe) {

        recipe.getIngredientQuantities().forEach(q -> q.setRecipe(recipe));
        recipe.getSteps().forEach(s -> s.setRecipe(recipe));

        return recipeRepository.save(recipe);
    }

    //deleting recipe by recipe id
    public void deleteRecipeById(Long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
        } else {
            throw new NotFoundException("Recipe with id: " + id + " already does not exist");
        }
    }

    //updating recipe
    public Recipe updateRecipe(Recipe recipe) {

        recipe.getIngredientQuantities().forEach(q -> q.setRecipe(recipe));
        recipe.getSteps().forEach(s -> s.setRecipe(recipe));

        Long id = recipe.getId();
        if (recipeRepository.existsById(id)) {
            return recipeRepository.save(recipe);
        } else {
            throw new NotFoundException("Recipe with id:" + id + " does not exist - cannot be updated!");
        }
    }
}
