package pl.biologicznieczynny.diycosmeticsdatabase.controllers;


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
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.services.IngredientService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    //get's

    @GetMapping("/ingredients")
    public Page<Ingredient> getIngredientListPageable(@RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size") int size,
                                                        @RequestParam(value = "sort", defaultValue = "asc") String sort) {
       return ingredientService.findAll(page, size, sort);
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        return ingredientService.findById(id);
    }

    //post's

    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addNewIngredient(ingredient);
    }

    //put's

    @PutMapping("/ingredients")
    public ResponseEntity<Ingredient> updateIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.updateIngredient(ingredient);
    }

    //delete's

    @DeleteMapping("/ingredients/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
    }

}
