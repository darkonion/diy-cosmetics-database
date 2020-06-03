package pl.biologicznieczynny.diycosmeticsdatabase.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.services.RecipeService;

import java.util.List;

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
        return recipeService.findAll(page, size, cat);
    }

    @GetMapping("/recipes/{id}")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.findById(id);
    }

    @GetMapping("/recipes/search")
    public List<Recipe> getSearchQueryResults(@RequestParam(defaultValue = "", required = false) String query) {
        return recipeService.findBySearchQuery(query);
    }

    //posts

    @PostMapping("/recipes")
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        return recipeService.addNewRecipe(recipe);
    }

    //delete's

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
    }


}
