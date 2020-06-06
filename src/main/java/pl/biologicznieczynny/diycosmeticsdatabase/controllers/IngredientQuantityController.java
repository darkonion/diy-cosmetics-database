package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.services.IngredientQuantityService;

import java.net.URI;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class IngredientQuantityController {

    private final IngredientQuantityService quantityService;

    public IngredientQuantityController(IngredientQuantityService quantityService) {this.quantityService = quantityService;}


    @DeleteMapping("/quantities/{id}")
    public void deleteIngredientQuantityById(@PathVariable Long id) {
        log.info("Deleting Ingredient Quantity with id: " + id);
        quantityService.deleteQuantity(id);
    }

    @PostMapping("/recipes/{id}/quantities")
    public ResponseEntity<Recipe> addNewQuantityToRecipe(@PathVariable Long id, @RequestBody IngredientQuantity ingredientQuantity) {
        log.info("Adding new Ingredient Quantity: " + ingredientQuantity.getIngredient().getName() + " to recipe: " + id);

        Recipe savedRecipeWithNewQuantity = quantityService.addNewQuantityToRecipe(id, ingredientQuantity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRecipeWithNewQuantity.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(savedRecipeWithNewQuantity);
    }
}
