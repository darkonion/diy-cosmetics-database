package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.services.RecipeService;

import java.net.URI;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {this.recipeService = recipeService;}

    //gets

    @GetMapping("/recipes")
    public Page<Recipe> getRecipesListPageable(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                @RequestParam(value = "size", defaultValue = "6", required = false) int size,
                                                @RequestParam(value = "cat", required = false) Long cat) {
        log.info(String.format("Getting recipes page of size:%s page number:%s categoryId:%s", size, page, cat));
        return recipeService.findAll(page, size, cat);
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        log.info("Getting recipe with id: " + id);
        return recipeService.findById(id);
    }

    @GetMapping("/recipes/search")
    public List<Recipe> getSearchQueryResults(@RequestParam(defaultValue = "", required = false) String query) {
        log.info("Getting search results for query: " + query);
        return recipeService.findBySearchQuery(query);
    }

    //posts

    @PostMapping("/recipes")
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        log.info("Persisting new recipe: " + recipe.getName());
        Recipe savedRecipe = recipeService.addNewRecipe(recipe);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRecipe.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(savedRecipe);
    }

    //delete's

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        log.info("Deleting recipe with id " + id);
        recipeService.deleteRecipeById(id);
    }

    //put's

    @PutMapping("/recipes")
    public ResponseEntity<Recipe> updateIngredient(@RequestBody Recipe recipe) {
        log.info("Updating recipe: " + recipe.getName());
        Recipe updatedRecipe = recipeService.updateRecipe(recipe);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedRecipe.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(updatedRecipe);
    }


}
