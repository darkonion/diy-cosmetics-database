package pl.biologicznieczynny.diycosmeticsdatabase.controllers;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    public IngredientController(IngredientService ingredientService) {this.ingredientService = ingredientService;}

    //gets

    @GetMapping("/ingredients")
    public Page<Ingredient> getIngredientListPageable(@RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size") int size,
                                                        @RequestParam(value = "sort") String sort) {
       return ingredientService.findAll(page, size, sort);
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getIngredientById(@PathVariable Long id) {
        return ingredientService.findById(id);
    }

    //posts

    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredient) {

        return ingredientService.addNewIngredient(ingredient);
    }


}
