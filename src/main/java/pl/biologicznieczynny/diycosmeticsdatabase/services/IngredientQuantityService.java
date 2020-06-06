package pl.biologicznieczynny.diycosmeticsdatabase.services;

import org.springframework.stereotype.Service;
import pl.biologicznieczynny.diycosmeticsdatabase.exceptionHandling.NotFoundException;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.IngredientQuantityRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;

@Service
public class IngredientQuantityService {

    private final IngredientQuantityRepository quantityRepository;
    private final RecipeRepository recipeRepository;

    public IngredientQuantityService(IngredientQuantityRepository quantityRepository,
            RecipeRepository recipeRepository) {this.quantityRepository = quantityRepository;
        this.recipeRepository = recipeRepository;
    }

    //deleting ingredient quantity by id
    public void deleteQuantity(Long id) {
        if (!quantityRepository.existsById(id)) {
            throw new NotFoundException("Ingredient Quantity with id: " + id + " not found!");
        }
        quantityRepository.deleteById(id);
    }

    //adding new ingredient quantity to existing recipe
    public Recipe addNewQuantityToRecipe(Long id, IngredientQuantity ingredientQuantity) {
        Recipe recipe = recipeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe with id: " + id + " not found!"));

        ingredientQuantity.setRecipe(recipe);
        recipe.getIngredientQuantities().add(ingredientQuantity);

        return recipeRepository.save(recipe);
    }
}
